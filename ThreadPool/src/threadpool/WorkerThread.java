/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadpool;

/**
 *
 * @author nguyendung
 */
public class WorkerThread implements Runnable {
     
    private final String command;
     
    public WorkerThread(String s){
        this.command=s;
    }
 
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }
 
    private void processCommand() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
        }
    }
 
    @Override
    public String toString(){
        return this.command;
    }
}
