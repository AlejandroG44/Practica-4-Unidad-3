
package app;
import java.util.Random;

public class Puzzle8 {
    
    private int[][] puzzle={{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
    private int x;
    private int y;
    // Valida si un valor existe dentro del tablero
    private boolean existe(int v){
        boolean existe=false;
        for(int i=0;i<3 && !existe;i++)
            for(int j=0;j<3;j++)
                if(puzzle[i][j]==v){
                    existe=true;
                    break;
                }
        return existe;
    }
    // Genera los 9 valores para llenar el tablero 
    private void genera(){
        Random r= new Random();
        
        for(int i=0,v;i<9;i++){
            do{
              v=r.nextInt(9);
            } while(existe(v));
            puzzle[i/3][i%3]=v;
            if(v==0){
                x=i/3;y=i%3;
            }
        }
    }
    // Constructor del puzzle8
    public Puzzle8(){
        genera();
    }
    // Mueve el 0 arriba devuelve true si lo hizo y false en caso contrario
    public boolean arriba(){
        boolean test=false;
        if(x>0){
            puzzle[x][y]=puzzle[x-1][y];
            puzzle[x-1][y]=0;
            x--;
            test=true;
        }
        return test;
    }
    // Mueve el 0 abajo devuelve true si lo hizo y false en caso contrario
    public boolean abajo(){
        boolean test=false;
        if(x<2){
            puzzle[x][y]=puzzle[x+1][y];
            puzzle[x+1][y]=0;
            x++;
            test=true;
        }
        return test;
    }
    // Mueve el 0 a la derecha devuelve true si lo hizo y false en caso contrario
    public boolean derecha(){
        boolean test=false;
         if(y<2){
            puzzle[x][y]=puzzle[x][y+1];
            puzzle[x][y+1]=0;
            y++;
            test=true;
        }
        return test;
    }
    // Mueve el 0 a la izquierda devuelve true si lo hizo y false en caso contrario
    public boolean izquierda(){
        boolean test=false;
         if(y>0){
            puzzle[x][y]=puzzle[x][y-1];
            puzzle[x][y-1]=0;
            y--;
            test=true;
        }
        return test;
    }
    // Retornar el tablero para ser utilizado en aplicaciÃ³n
    public int[][] getPuzzle(){
        return puzzle;
    }
    // Verifica si el tablero esta en el estado final de terminaciÃ³n, devuelve true si asi es en caso contrario devuelve false.
    public boolean haTerminado(){
        boolean test=true;
        int v[][]={{1,2,3},{8,0,4},{7,6,5}};
        for(int i=0;i<9;i++)
           if(puzzle[i/3][i%3]!=v[i/3][i%3]){
               test=false;
               break;
           }
        return test;
    }
    
}
