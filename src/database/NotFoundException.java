package database;

public class NotFoundException extends Exception{
    NotFoundException(String obj, String id) {
        super("\u001B[31m" + obj + " with ID: " + id + " doesn't exist" + "\u001B[0m");
    }
}
