package Exceptions;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String obj, String id, String src, String list) {
        super("\u001B[31m" + obj + " (with ID: " + id + ") already exists in \"" + src + "\"'s " + list + "\u001B[0m");
    }
    //for assignment only (assignments don't have names)
    public AlreadyExistsException(String id, String src, String list) {
        super("\u001B[31m" + "Assignment (with ID: " + id + ") already exists in \"" + src + "\"'s " + list + "\u001B[0m");
    }
}
