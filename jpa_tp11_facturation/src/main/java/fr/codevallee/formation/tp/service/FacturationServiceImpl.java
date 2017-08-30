package fr.codevallee.formation.tp.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.codevallee.formation.tp.modele.Address;
import fr.codevallee.formation.tp.modele.AddressType;
import fr.codevallee.formation.tp.modele.Article;
import fr.codevallee.formation.tp.modele.Bill;
import fr.codevallee.formation.tp.modele.BillLine;
import fr.codevallee.formation.tp.modele.Client;
import fr.codevallee.formation.tp.modele.Description;
import fr.codevallee.formation.tp.modele.Status;
import fr.codevallee.formation.tp.repositories.FacturationRepository;


@Component
public class FacturationServiceImpl {

	@Autowired
	private FacturationRepository facturationRepository;
	
	public void initDatabase() {

		// Create a billing address
		Address billingAddress = new Address();
		billingAddress.setNumber(20);
		billingAddress.setStreet("rue Marie Curie");
		billingAddress.setPostalCode(Integer.valueOf(69009));
		billingAddress.setType(AddressType.BILLING);

		facturationRepository.insert(billingAddress);
		
		// Create a delivery address
		Address deliveryAddress = new Address();
		deliveryAddress.setNumber(10);
		deliveryAddress.setStreet("av Berthlot");
		deliveryAddress.setPostalCode(Integer.valueOf(69003));
		deliveryAddress.setType(AddressType.DELIVERY);
		
		facturationRepository.insert(deliveryAddress);
		
		// Create a client
		Client mariaC = new Client();
		mariaC.setFirstname("Maria");
		mariaC.setLastname("Carrey");
		mariaC.setDeliveryAddress(billingAddress);
		mariaC.setBillingAddress(deliveryAddress);
		
		facturationRepository.insert(mariaC);
		
		// Create a description for hammer
		Description hammerDesc = new Description();
		hammerDesc.setDescription("It's a hammer");
		
		facturationRepository.insert(hammerDesc);
		
		// Create an article : hammer
		Article hammer = new Article();
		hammer.setPrice(20);
		hammer.setReference("M_1");
		hammer.setDescription(hammerDesc);

		facturationRepository.insert(hammer);
		
		// Create a description for nail
		Description nailDesc = new Description();
		nailDesc.setDescription("It's a nail");

		facturationRepository.insert(nailDesc);
		
		// Create an article : nailSteel
		Article nailSteel = new Article();
		nailSteel.setPrice(2);
		nailSteel.setReference("NS_1");
		nailSteel.setDescription(nailDesc);

		facturationRepository.insert(nailSteel);
		
		// Create an article : nailGlazier
		Article nailGlazier = new Article();
		nailGlazier.setPrice(5);
		nailGlazier.setReference("NG_1");
		nailGlazier.setDescription(nailDesc);
		
		facturationRepository.insert(nailGlazier);
		
		// Create a bill line for hammer
		BillLine hammerLine = new BillLine();
		hammerLine.setArticle(hammer);
		hammerLine.setNumberOfArticles(3);

		facturationRepository.insert(hammerLine);
		
		// Create a bill line for nailSteel
		BillLine nailSteelLine = new BillLine();
		nailSteelLine.setArticle(nailSteel);
		nailSteelLine.setNumberOfArticles(25);

		facturationRepository.insert(nailSteelLine);
		
		// Create a bill line for nailGlazier
		BillLine nailGlazierLine = new BillLine();
		nailGlazierLine.setArticle(nailGlazier);
		nailGlazierLine.setNumberOfArticles(10);

		facturationRepository.insert(nailGlazierLine);
		
		Set<BillLine> lines = new HashSet<>();
		lines.add(hammerLine);
		lines.add(nailSteelLine);
		lines.add(nailGlazierLine);

		
		// Create a bill
		Bill mariaBill = new Bill();
		mariaBill.setBillLines(lines);
		mariaBill.setClient(mariaC);
		mariaBill.setInvoiceDate(Calendar.getInstance().getTime());
		mariaBill.setStatus(Status.PAYED);

		facturationRepository.insert(mariaBill);

	}

}
