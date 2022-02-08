package com.jb.bankapp.Interceptor;

import com.jb.bankapp.Beans.Account;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AccountInterceptor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Account && beanName.equals("account")) {
            Account account = (Account) bean;
            if (account.getClient().getAccounts().size() > 0)
            account.setAccountNumber(
                    account.getClient().getAccounts().get(account.getClient().getAccounts().size()-1)
            .getAccountNumber()+1);
            else {
                account.setAccountNumber(account.getClient().getNationalId()*10+1);
            }
            return bean;
        }
        else return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
