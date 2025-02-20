package ch.etmles.payroll.Controllers.Exceptions;

public class RessourceDeleteNotFound extends RuntimeException {
    public RessourceDeleteNotFound(Long id, String ressourceName) {
        super("Could not delete " + ressourceName + " (not found): " + id);
    }
}
