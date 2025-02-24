package ch.etmles.payroll.Exceptions;

public class RessourceIDNotFound extends RuntimeException {
    public RessourceIDNotFound(Long id, String ressourceName) {
        super("Could not find " + ressourceName + " " + id);
    }
}
