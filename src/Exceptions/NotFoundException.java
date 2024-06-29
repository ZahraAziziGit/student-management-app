package Exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String obj, String id) {
        super("\u001B[31m" + obj + " (with ID: " + id + ") doesn't exist" + "\u001B[0m");
    }
    //for lists
    public NotFoundException(String obj, String id, String user, String list) {
        super("\u001B[31m" + obj + " (with ID: " + id + ") doesn't exist in \"" + user + "\"'s " + list + "\u001B[0m");
    }
    //for assignment only (assignments don't have names)
    public NotFoundException(String id, String user, String list) {
        super("\u001B[31m" + "Assignment (with ID: " + id + ") doesn't exist in \"" + user + "\"'s " + list + "\u001B[0m");
    }

}
