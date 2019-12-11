package classes;
import java.util.Arrays;

public class Genes {
    private int[] genes;
    private int size;
    private int numOfGenes;

    public Genes(int size, int numOfGenes){
        genes = new int[size];
        this.size = size;
        this.numOfGenes = numOfGenes;
        fillGenes();
        checkGenes();
    }

    /*@Override dorobic toString dla tablicy genow (moze nawet cale wypisywanie
    public String toString()*/

    public int[] getGenes(){
        return genes;
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
        int[] counter = new int[8];
        boolean flag = true;

        while (flag) {
            flag = false;
            Arrays.fill(flags, false);

            for (int i = 0; i < size; i++)
                flags[genes[i]] = true;

            for (int i = 0; i < numOfGenes; i++)
                if (!flags[i])
                    flag = true;

            if(flag){
                for(int i = 0; i < numOfGenes; i++){
                    if(!flags[i])
                        genes[(int)(Math.random() * numOfGenes)] = i;
                }
            }
        }
        Arrays.sort(genes);
    }

    public int randomGenes()
}
