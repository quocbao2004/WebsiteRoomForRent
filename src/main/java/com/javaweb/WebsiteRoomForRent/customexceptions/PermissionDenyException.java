package com.javaweb.WebsiteRoomForRent.customexceptions;

public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message) {
        super(message);
    }
}