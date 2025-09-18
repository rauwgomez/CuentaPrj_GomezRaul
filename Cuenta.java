import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta
{
    // Atributos de la clase
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    
    // Atributos de Clase
    private static int cantCuentasCreadas;
    
    // Constructor
    public Cuenta(double pSaldo){
        this("N/A", pSaldo);
    }
    
    public Cuenta(String nombreCuentaHabiente, double pSaldo){
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        codCuenta = codCuenta + (++cantCuentasCreadas);
        fechaCreacion = establecerFechaCreacion();
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
        
    }
    
    // Metodos
    public void setNombreCuentaHabiente(String pNombre){
        nombreCuentaHabiente = pNombre;
    }
    
    public String getNombreCuentaHabiente() {
        return nombreCuentaHabiente;
    }
    
    public String getCodCuenta(){
        return codCuenta;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    public double depositar(double monto){
        saldo += monto;
        cantDepositosRealizados++;
        return saldo;
    }
    
    public double retirar(double monto){
        if(validarRetiro(monto)){
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        
        return saldo;
    }
    
    private boolean validarRetiro(double monto){
        return monto > 0 && monto <= saldo;
    }
    
    private String establecerFechaCreacion(){
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
    
    public String toString(){
        String estado = "";
    
        estado += "========== Cuenta ==========\n";
        estado += "Código: \t\t" + codCuenta + "\n";
        estado += "Titular: \t\t" + nombreCuentaHabiente + "\n";
        estado += "Fecha Creación: \t" + fechaCreacion + "\n";
    
        estado += "Saldo: \t\t\t₡" + String.format("%.2f", saldo) + "\n";
        estado += "----------------------------------------\n";
        estado += "Depositos Realizados: \t" + cantDepositosRealizados + "\n";
        estado += "Retiros Exitosos: \t" + cantRetirosExitososRealizados + "\n";
        estado += "========================================\n";

        return estado; 
    }
}