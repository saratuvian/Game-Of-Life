public class GOLMatrix {
  private boolean [][] world;
  private int generations;
    public GOLMatrix(int n){
        if (n<3){
            n=3;
        }
      world = new boolean [n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                world[i][j]=false;
            }
        }
        generations=0;
    }
    public void flipCell(int row, int col){
        try {
            world[row][col] = !world[row][col];
        } catch (IndexOutOfBoundsException e){}
    }
    public boolean[][] getWorld() {
        boolean[][] temp2 = new boolean[world.length][world.length];
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                temp2[i][j] = world[i][j];
            }
        }
        return temp2;
    }

    public int getGenerations() {
        return generations;
    }

    public void clearWorld(){
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                world[i][j]=false;
            }
        }
        generations=0;
    }
    public void nextGeneration(){
        boolean [][] temp1= new boolean[world.length][world.length];
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                int x=LiveN(i,j);
                if (world[i][j]==true){
                    if (x<2){
                        temp1[i][j]=false;
                    }
                    if (x==2 || x==3){
                        temp1[i][j]=true;
                    }
                    if (x>3) {
                        temp1[i][j] = false;
                    }
                }
                else {
                    if (x==3){
                        temp1[i][j]=true;
                    }
                }
            }
        }
        world=temp1;
        generations++;
        }

    private int LiveN(int row,int col) {
        int counter = 0;
        if (world[row][col]) {
            counter--;
        }
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (i >= 0 && i < world.length && j >= 0 && j < world.length) {
                    if (world[i][j] == true) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
