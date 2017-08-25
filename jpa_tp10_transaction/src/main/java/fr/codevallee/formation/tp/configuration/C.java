package fr.codevallee.formation.tp.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import fr.codevallee.formation.tp.service.MairieServiceImpl;

@Component
public class C implements ApplicationContextAware {

	public static C i;

	private MairieServiceImpl mairieServiceImpl = null;
	public P p = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		i = this;
		mairieServiceImpl = applicationContext.getBean(MairieServiceImpl.class);
		p = applicationContext.getBean(P.class);
	}

	public MairieServiceImpl getMairieServiceImpl() {
		return mairieServiceImpl;
	}

}