/*
 DA ELIMINARE

 package grafica;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import negozio.Magazzino;
import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;

public class AddProductListener implements ActionListener{

	private JAddProductPanel jAddProductPanel;
	private Magazzino magazzino;
	
	public AddProductListener(JAddProductPanel jAddProductPanel, Magazzino magazzino)
	{
		this.jAddProductPanel = jAddProductPanel;
		this.magazzino = magazzino;
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cambia"))
		{
			//FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File("C:\\Users\\alessio\\ecommerce\\Java-eCommerce\\media\\img"));
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this.jAddProductPanel);
			
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			ImageIcon newIcon = new ImageIcon(this.jAddProductPanel.ridimensionaImmagine(fc.getSelectedFile()),fc.getSelectedFile().toString());
			if(newIcon!=null)
			{
				this.jAddProductPanel.jImmagineLabel.setIcon(newIcon);
			}
			
		}
		else if(e.getActionCommand().equals("aggiungi"))
		{
			ArrayList <Prodotto> articoli = new <Prodotto> ArrayList();
			String prezzo;
			String quantita;
			Promozione promo;
			int controllo = 0;
			articoli = magazzino.getArticoli();
			

			for(int i = 0;i<articoli.size();i++)
			{
				if(articoli.get(i).getCodice().equals(this.jAddProductPanel.jCodiceTextField.getText()))
				{
					JOptionPane.showMessageDialog(this.jAddProductPanel, "Il prodotto che si vuole inserire è gia presente in magazzino","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					controllo = 1;
				}
			}
			
			if(controllo == 0)
			{
				if(this.jAddProductPanel.jOffertaNoRadioButton.isSelected())
				{
					if(this.jAddProductPanel.jCodiceTextField.getText().equals("") || this.jAddProductPanel.jNomeTextField.getText().equals("") || this.jAddProductPanel.jMarcaTextField.getText().equals("") || this.jAddProductPanel.jCategoriaTextField.getText().equals("") ||this.jAddProductPanel.jPrezzoTextField.getText().equals("") || this.jAddProductPanel.jQuantitaTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						ImageIcon imgicon = (ImageIcon) this.jAddProductPanel.jImmagineLabel.getIcon();
						
						prezzo = this.jAddProductPanel.jPrezzoTextField.getText();
						quantita = this.jAddProductPanel.jQuantitaTextField.getText();
						this.jAddProductPanel.prodotto = new Prodotto(this.jAddProductPanel.jNomeTextField.getText(),this.jAddProductPanel.jMarcaTextField.getText(),this.jAddProductPanel.jCodiceTextField.getText(),this.jAddProductPanel.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita));
						magazzino.aggiungiProdotto(this.jAddProductPanel.prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						System.out.println(this.jAddProductPanel.prodotto);
					}
				}
				else if(this.jAddProductPanel.jOffertaPercentualeRadioButton.isSelected())
				{
					if(this.jAddProductPanel.jCodiceTextField.getText().equals("") || this.jAddProductPanel.jNomeTextField.getText().equals("") || this.jAddProductPanel.jMarcaTextField.getText().equals("") || this.jAddProductPanel.jCategoriaTextField.getText().equals("") ||this.jAddProductPanel.jPrezzoTextField.getText().equals("") || this.jAddProductPanel.jQuantitaTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(this.jAddProductPanel.jPercentualeTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Inserire il valore della percentuale da scontare","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						ImageIcon imgicon = (ImageIcon) this.jAddProductPanel.jImmagineLabel.getIcon();
						
						promo = new ScontoPercentuale(Integer.parseInt(this.jAddProductPanel.jPercentualeTextField.getText()));
						prezzo = this.jAddProductPanel.jPrezzoTextField.getText();
						quantita = this.jAddProductPanel.jQuantitaTextField.getText();
						this.jAddProductPanel.prodotto = new Prodotto(this.jAddProductPanel.jNomeTextField.getText(),this.jAddProductPanel.jMarcaTextField.getText(),this.jAddProductPanel.jCodiceTextField.getText(),this.jAddProductPanel.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita),promo);
						magazzino.aggiungiProdotto(this.jAddProductPanel.prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(this.jAddProductPanel.jOffertaTrePerDueRadioButton.isSelected())
				{
					if(this.jAddProductPanel.jCodiceTextField.getText().equals("") || this.jAddProductPanel.jNomeTextField.getText().equals("") || this.jAddProductPanel.jMarcaTextField.getText().equals("") || this.jAddProductPanel.jCategoriaTextField.getText().equals("") ||this.jAddProductPanel.jPrezzoTextField.getText().equals("") || this.jAddProductPanel.jQuantitaTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this.jAddProductPanel, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						ImageIcon imgicon = (ImageIcon) this.jAddProductPanel.ImmagineLabel.getIcon();
						
						promo = new ScontoTrePerDue();
						prezzo = this.jAddProductPanel.jPrezzoTextField.getText();
						quantita = this.jQuantitaTextField.getText();
						this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita),promo);
						magazzino.aggiungiProdotto(prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		
		
	}
}
*/