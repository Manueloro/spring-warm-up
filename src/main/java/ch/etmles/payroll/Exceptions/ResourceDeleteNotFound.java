package ch.etmles.payroll.Exceptions;

public class ResourceDeleteNotFound extends RuntimeException {
    public ResourceDeleteNotFound(Long id, String resourceName) {
        super("Could not delete " + resourceName + " (not found): " + id);
    }
}
