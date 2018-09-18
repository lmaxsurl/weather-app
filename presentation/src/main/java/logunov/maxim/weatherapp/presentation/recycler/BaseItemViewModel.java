package logunov.maxim.weatherapp.presentation.recycler;

import logunov.maxim.domain.DomainModel;

public abstract class BaseItemViewModel<Entity extends DomainModel> {

    public abstract void setItem(Entity entity, int position);

    public void onItemClick(){

    }
}
