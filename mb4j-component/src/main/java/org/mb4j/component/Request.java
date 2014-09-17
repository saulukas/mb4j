package org.mb4j.component;

import org.mb4j.component.form.Form;
import org.mb4j.component.form.Form4Response;
import org.mb4j.component.form.Form4ResponseResolver;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPathReader;
import org.mb4j.component.utils.Attributes;

public class Request {

    private final ViewLocator viewLocator;
    private final UrlPathReader urlPathReader;
    private final Attributes attributes;
    private final AssetUrlResolver assetUrlResolver;
    private final ViewUrlResolver viewLocatorResolver;
    private final Form4ResponseResolver formDataResolver;
    private final ResourceUrlResolver resourcesResolver;

    public Request(
            ViewLocator viewLocator,
            Attributes attributes,
            AssetUrlResolver assetUrlResolver,
            ViewUrlResolver viewLocatorResolver,
            Form4ResponseResolver formDataResolver,
            ResourceUrlResolver resourcesResolver
    ) {
        this.viewLocator = viewLocator;
        this.urlPathReader = BufferedUrlPathReader.of(viewLocator.params.path);
        this.attributes = attributes;
        this.assetUrlResolver = assetUrlResolver;
        this.viewLocatorResolver = viewLocatorResolver;
        this.formDataResolver = formDataResolver;
        this.resourcesResolver = resourcesResolver;
    }

    public ViewLocator viewLocator() {
        return viewLocator;
    }

    public boolean hasMorePathSegments() {
        return urlPathReader.hasMoreSegments();
    }

    public String readPathSegment() {
        return urlPathReader.readSegment();
    }

    public ViewUrl resolve(ViewLocator url) {
        return viewLocatorResolver.resolve(url);
    }

    public AssetUrl assetUrl(String assetUrl) {
        return assetUrlResolver.resolveUrl(assetUrl);
    }

    public Form4Response resolve(Form<?> formData) {
        return formDataResolver.resolve(formData);
    }

    public ResourceUrl resourceUrl(Component component, String resourceName) {
        return resourcesResolver.resourceUrl(component, resourceName);
    }

    public Attributes attributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + urlPathReader + "]";
    }
}
