package ru.cmo.edu.data.resource;

import org.springframework.hateoas.ResourceSupport;

public class BaseResource extends ResourceSupport {
    private String linkCaption;
    private String linkSubCaption;

    public String getLinkCaption() {
        return linkCaption;
    }

    public void setLinkCaption(String linkCaption) {
        this.linkCaption = linkCaption;
    }

    public String getLinkSubCaption() {
        return linkSubCaption;
    }

    public void setLinkSubCaption(String linkSubCaption) {
        this.linkSubCaption = linkSubCaption;
    }
}
