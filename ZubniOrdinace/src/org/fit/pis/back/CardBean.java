package org.fit.pis.back;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import org.fit.pis.data.Card;
import org.fit.pis.data.Illness;
import org.fit.pis.service.CardManager;

@ManagedBean
@SessionScoped
public class CardBean {
	@EJB
	private CardManager cardMgr;
	private Card card;
	private Illness illness; 
    private HtmlDataTable listTable;
    private HtmlDataTable illnesslistTable;
	
	public CardBean() {
		card = new Card();
		illness = new Illness();
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
    public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public List<Card> getCards() {
		return cardMgr.findAll();
	}
	
	public HtmlDataTable getListTable() 
	{
		return listTable;
	}
	
	public void setListTable(HtmlDataTable listTable) 
	{
		this.listTable = listTable;
	}
	
	public HtmlDataTable getIllnesslistTable() {
		return illnesslistTable;
	}

	public void setIllnesslistTable(HtmlDataTable illnesslistTable) {
		this.illnesslistTable = illnesslistTable;
	}

	// ====================================
	
	public String actionView()
    {
    	setCard((Card) listTable.getRowData());
    	return "view";
    }
    
	public String actionNew()
	{
        card = new Card();
        return "newcard";
	}
	
	public String actionInsertNew()
    {
		card.setRegDate(new Date());
        cardMgr.save(card);
        return "insert";
    }
	
	public String actionUpdate()
    {
	   cardMgr.save(card);
        return "update";
    }
	
	public String actionIllnessNew() {
		illness = new Illness();
		return "newillness";
	}
	
	public String actionIllnessAdd() {
		illness.setFoundDate(new Date());
		illness.setPatient(card);
        card.getIllnesses().add(illness);
        return "addillness";
	}
}
