package pb.wi.kck.services;

import pb.wi.kck.model.ProductBlueprint;

public interface ElementService<T> {
    public T saveUpdateElement(T t);
    public T findElementById(Integer id);

}
