
package com.amc.api.theatres.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    private Self self;
    private Next next;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

}
