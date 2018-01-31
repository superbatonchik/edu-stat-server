package ru.cmo.edu.data.resource;

import org.springframework.hateoas.ResourceSupport;

public class BaseResource extends ResourceSupport {
    private String linkCaption;
    private String linkSubCaption;
    private String additionalText;

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

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }
}
