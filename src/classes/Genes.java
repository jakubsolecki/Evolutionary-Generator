package classes;
import java.util.Arrays;

public class Genes {
    private int[] genes;
    private int size;
    private int numOfGenes;

    public Genes(int size, int numOfGenes){
        this.size = size;
        genes = new int[size];
        this.numOfGenes = numOfGenes;
        fillGenes();
        checkGenes();
    }

    /* @Override dorobic toString dla tablicy genow (moze nawet cale wypisywanie
    public String toString())*/


    public int[] getGenes(){
        return genes;
    }


    public Genes(Genes g) {
        this(g.getNumOfGenes(), g.getSize());
        genes = Arrays.copyOf(g.getGenes(), size);
    }


    public Genes(Genes g1, Genes g2) {
        this(g1.getNumOfGenes(), g1.getSize());

        if (g1.getSize() != g2.getSize()) throw new IllegalArgumentException("Gens have different sizes");
        if (g1.getNumOfGenes() != g2.getNumOfGenes())
            throw new IllegalArgumentException("Gens have different range of values");

        // random places to div DNA
        int firstPlaceToDiv = (int) (Math.random() * (size - 1));
        int secondPlaceToDiv = firstPlaceToDiv;
        while (secondPlaceToDiv == firstPlaceToDiv) {
            secondPlaceToDiv = (int) (Math.random() * (size - 1));
        }
        if (firstPlaceToDiv > secondPlaceToDiv) {
            int tmp = firstPlaceToDiv;
            firstPlaceToDiv = secondPlaceToDiv;
            secondPlaceToDiv = tmp;
        }

        // FILLING GENES BY 2 PARTS OF FIRST PARENT'S DNA AND 1 PART OF SECOND PARENT'S DNA
        for (int i = 0; i <= firstPlaceToDiv; i++) {
            genes[i] = g1.getGenes()[i];
        }
        for (int i = firstPlaceToDiv + 1; i <= secondPlaceToDiv; i++) {
            genes[i] = g2.getGenes()[i];
        }
        for (int i = secondPlaceToDiv; i < size; i++) {
            genes[i] = g1.getGenes()[i];
        }

        //REPAIR GENES, THERE IS A CHANCE THAT SOME GENS FROM RANGE AREN'T EXIST IN CHILD GENES
        checkGenes();

    }


    public int getSize(){
        return size;
    }


    public int getNumOfGenes(){
        return numOfGenes;
    }


    private void fillGenes(){
        for(int i = 0; i < size; i++){
            genes[i] = (int)(Math.random() * numOfGenes);
        }
        Arrays.sort(genes);
    }


    private void checkGenes(){
        boolean[] flags = new boolean[8];
        //int[] counter = new int[8];
        boolean flag = true;

        while (flag) {
            flag = false;
            Arrays.fill(flags, false);

            for (int i = 0; i < size; i++)
                flags[genes[i]] = true;

            for (int i = 0; i < numOfGenes; i++) {
                if (!flags[i]) {
                    flag = true;
                    break;
                }
            }

            if(flag){
                for(int i = 0; i < numOfGenes; i++){
                    if(!flags[i])
                        genes[(int)(Math.random() * numOfGenes)] = i;
                }
            }
        }
        Arrays.sort(genes);
    }


    public int returnRandomGen() {
        int rand = (int) (Math.random() * (size));
        return genes[rand];
    }
}
