package org.codemash.runnerz.beans;

public class FooService {

    private final SharedService sharedService;

    public FooService(SharedService sharedService) {
        this.sharedService = sharedService;
    }
}
