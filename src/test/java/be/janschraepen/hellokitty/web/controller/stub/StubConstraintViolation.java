package be.janschraepen.hellokitty.web.controller.stub;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Created by jan on 05/06/15.
 */
public class StubConstraintViolation<T> implements ConstraintViolation<T> {

    private String messageTemplate;

    public StubConstraintViolation(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }

    @Override
    public T getRootBean() {
        return null;
    }

    @Override
    public Class<T> getRootBeanClass() {
        return null;
    }

    @Override
    public Object getLeafBean() {
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return new Object[0];
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Path getPropertyPath() {
        return new StubPath();
    }

    @Override
    public Object getInvalidValue() {
        return null;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }

    @Override
    public <U> U unwrap(Class<U> uClass) {
        return null;
    }

}
