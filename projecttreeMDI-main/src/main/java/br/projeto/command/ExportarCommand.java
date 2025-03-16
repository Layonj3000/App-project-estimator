
package br.projeto.command;

public class ExportarCommand implements Command{

    public String formato;
    
    public ExportarCommand(String formato) {
        this.formato = formato;
    }

    @Override
    public void execute() {
        System.out.println(formato);
    }
    
    
}
