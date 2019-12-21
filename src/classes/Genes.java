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

    // TODO: remove?
    public Genes(Genes g) {
        this(g.getNumOfGenes(), g.getSize());
        genes = Arrays.copyOf(g.getGenes(), size);
    }

    // constructor for child
    public Genes(Genes g1, Genes g2) {
        this(g1.getSize(), g1.getNumOfGenes());
        // this.numOfGenes = g1.getNumOfGenes();
        // this.size = g1.getSize();

        if (g1.getSize() != g2.getSize())
            throw new IllegalArgumentException("Gens have different sizes"); // rather impossible, but you never know
        if (g1.getNumOfGenes() != g2.getNumOfGenes())
            throw new IllegalArgumentException("Gens have different range of values"); // same

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
        for (int i = 0; i <= firstPlaceToDiv; i++)
            genes[i] = g1.getGenes()[i];

        for (int i = firstPlaceToDiv + 1; i <= secondPlaceToDiv; i++)
            genes[i] = g2.getGenes()[i];

        for (int i = secondPlaceToDiv; i < size; i++)
            genes[i] = g1.getGenes()[i];

        //REPAIR GENES, THERE IS A CHANCE THAT SOME GENS FROM RANGE DON'T EXIST IN CHILD GENES
        checkGenes();
    }


    private void fillGenes(){
        for(int i = 0; i < size; i++){
            genes[i] = (int)(Math.random() * numOfGenes);
        }
        Arrays.sort(genes);
    }


    // verifying that there are all possible genes in one's genotype
    private void checkGenes(){
        boolean flag = true;

        while (flag) {
            flag = false;
            boolean[] flags = new boolean[numOfGenes + 1];
            Arrays.fill(flags, false);

            for (int i = 0; i < size; i++)
                flags[this.genes[i]] = true;

            for (int i = 0; i < numOfGenes; i++) {
                if (!flags[i]) {
                    flag = true;
                    break;
                }
            }

            if(flag){
                for(int i = 0; i < numOfGenes; i++){
                    if(!flags[i])
                        genes[(int)(Math.random() * size)] = i;
                }
            }
        }
        Arrays.sort(genes);
    }


    // family of get- methods
    public int[] getGenes(){
        return genes;
    }

    public int getRandomGen() {
        int rand = (int) (Math.random() * size);
        return genes[rand];
    }

    public int getSize(){
        return size;
    }

    public int getNumOfGenes(){
        return this.numOfGenes;
    }


}
