

class Filosofo extends Thread{
    private int id;
    private Tenedor tenedorizq;
    private Tenedor tenedordch;

    public Filosofo(int id, Tenedor tenedorizq, Tenedor tenedordch){
        this.id = id;
        this.tenedorizq = tenedorizq;
        this.tenedordch = tenedordch;
    }
    @Override
    public void run(){
        try{
            while(true){
                pensar();
                cojerTenedores();
                comer();
                soltarTenedores();
                
                break;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void pensar() throws InterruptedException{
        System.out.println("Filosofo " + this.id+" anda pensando");
        
        Thread.sleep((long)Math.random()*10000);
        
    }
    private void cojerTenedores() {
        System.out.println("Filosofo " + this.id+" esta intentando coger tenedores");
        
        synchronized(this.tenedorizq){
            System.out.println("Filosofo " + this.id+" ha cogido el tenedor izq");
        }
        synchronized(this.tenedordch){
            System.out.println("Filosofo " + this.id+" ha cogido el tenedor dch");
        }
    }
    private void comer() throws InterruptedException{
        System.out.println("Filosofo " + this.id+" anda comiendo");
        
        Thread.sleep((long)Math.random()*10000);
        
    }
    private void soltarTenedores(){
        System.out.println("Filosofo "+ this.id +" ha soltado los tenedores!");
    }


    
}
 public class Filosofos{
    public static void main(String[] args) {
        Filosofo[] filosfos = new Filosofo[5];
        Tenedor [] tenedores = new Tenedor[5];
        
        for(int i = 0 ; i<4;i++){
            tenedores[i]=  new Tenedor();
        }

        for(int i = 1 ; i<5;i++){
            Tenedor tenedorizq = tenedores[i];
            Tenedor tenedordch = tenedores[(i+1)%5];
            filosfos[i]=  new Filosofo(i, tenedorizq, tenedordch);
            filosfos[i].start();
        }
        
    }
}

class Tenedor{


}