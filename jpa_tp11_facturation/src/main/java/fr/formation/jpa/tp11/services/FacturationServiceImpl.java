package fr.formation.jpa.tp11.services;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.formation.jpa.tp11.model.Address;
import fr.formation.jpa.tp11.model.AddressType;
import fr.formation.jpa.tp11.model.Article;
import fr.formation.jpa.tp11.model.Bill;
import fr.formation.jpa.tp11.model.BillLine;
import fr.formation.jpa.tp11.model.Client;
import fr.formation.jpa.tp11.model.Description;
import fr.formation.jpa.tp11.model.Status;
import fr.formation.jpa.tp11.repositories.FacturationRepository;
import fr.formation.jpa.tp11.repositories.IRepository;


public class FacturationServiceImpl {

	private IRepository facturationRepository;
	
	/**
	 * @param facturationRepository
	 */
	public FacturationServiceImpl() {
		this.facturationRepository = new FacturationRepository();
	}

	public void initDatabase() {

		// Description for hammer
		Description hammerDesc = new Description("It's a hammer");
		facturationRepository.insert(hammerDesc);
		
		// Article : hammer 	
		Article hammer = new Article(20, "H1", hammerDesc);		
		facturationRepository.insert(hammer);
		
		// Bill line for hammer
		BillLine hammerLine = new BillLine(hammer, 50);
		facturationRepository.insert(hammerLine);
		
		//-----------------------------------------------------
		// Description for nails
		Description nailDesc = new Description("It's a nail");
		facturationRepository.insert(nailDesc);
		
		// Article : nailSteel
		Article nailSteel = new Article(2, "S1", nailDesc);
		facturationRepository.insert(nailSteel);
		
		// Bill line for nailSteel
		BillLine nailSteelLine = new BillLine(nailSteel, 100);
		facturationRepository.insert(nailSteelLine);
		
		//-----------------------------------------------------
		// Article : nailGlazier
		Article nailGlazier = new Article(5, "G1", nailDesc);
		facturationRepository.insert(nailGlazier);
		
		// Bill line for nailGlazier
		BillLine nailGlazierLine = new BillLine(nailGlazier, 200);
		facturationRepository.insert(nailGlazierLine);
		
		//-----------------------------------------------------
		// Billing address
		Address billingAddress = new Address(20, "rue Marie Curie", Integer.valueOf(69009), AddressType.BILLING);	
		facturationRepository.insert(billingAddress);
		
		// Delivery address
		Address deliveryAddress = new Address(10, "av Berthlot", Integer.valueOf(69003), AddressType.DELIVERY);
		facturationRepository.insert(deliveryAddress);
		
		// Client
		Client mariaC = new Client("Maria", "Carrey", billingAddress, deliveryAddress);		
		facturationRepository.insert(mariaC);
		
		//-----------------------------------------------------
		
		Set<BillLine> lines = new HashSet<>();
		lines.add(hammerLine);
		lines.add(nailSteelLine);
		lines.add(nailGlazierLine);
		
		// Bill
		Bill mariaBill = new Bill(Calendar.getInstance().getTime(), Status.NOT_PAYED, mariaC, lines);
		facturationRepository.insert(mariaBill);
	}

	public <T> void find(String met, String stmt, Class<T> model) {
		List<T> list = facturationRepository.findAll(stmt, model);
		System.out.println(met + "Size = " + list.size());
		
		for (T t : list) {
			System.out.println(met + t.toString());
		}
	}
}
