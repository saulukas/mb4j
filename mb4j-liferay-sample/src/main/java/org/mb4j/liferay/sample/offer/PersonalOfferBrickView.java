package org.mb4j.liferay.sample.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.BakerView;

@Singleton
public class PersonalOfferBrickView extends BakerView<PersonalOfferBrick.Baker.Params> {
    @Inject
    public PersonalOfferBrickView(PersonalOfferBrick.Baker baker) {
        super(baker);
    }

    @Override
    protected PersonalOfferBrick.Baker.Params bakerParamsFrom(ViewRequest request) {
        return PersonalOfferBrick.Baker.Params.from(request);
    }
}
