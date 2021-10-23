package edu.eci.cvds.sampleprj.dao;


public class PersistenceException extends Exception{
    public  static final String V_CLIENTE="error al vetar al cliente";
    private static final Long SERIALVERSIONUID=1L;


    public PersistenceException (String message, Exception e){
        super(message+e.toString());

    }

    public PersistenceException (String msg){
        super(msg);

    }

}
