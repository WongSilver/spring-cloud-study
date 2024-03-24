package edu.wong.function;

@FunctionalInterface
public interface BeanCopyCallBack<S, T> {
    void callBack(S t, T s);
}
