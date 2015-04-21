// This program displays the results of a query on a database
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DatabaseGui extends JFrame
{
   private JLabel idLabel;
   private JLabel pwdLabel;
   private JTextField idField;
   private JPasswordField pwdField;
   private JTextField queryField;
   private boolean connected = false;
   private int admin;
   private int currentID;
   private int orderNum;
   //=====================================flags for hiding menu's
   private boolean bflag = false;
   private boolean dflag = false;
   private boolean eflag = false;
   private boolean sflag = false;
   
   private JButton bookButton;
   private JButton dvdButton;
   private JButton editButton;
   private JButton shopButton;
   
   //====================================shop buttons
   private JButton newcart;//create a new cart
   private JButton addtocart; // add stock item to cart
   private JButton removefromcart; //remove stock item from cart
   private JButton mycart; // what is in my cart
   private JButton dumpcart; // delete my cart
   private JButton purchase;// purchase the contents of my cart
   private JButton restock;// an admin can restock an item
   
   //=====================================edit buttons
   private JButton addUser;//admin create new user
   private JButton deleteUser;//an admin can delete a current user
   private JButton changeName;//a customer can change name
   private JButton changePass;//a customer can change password(this will not take effect untill next session)
   private JButton changeEmail;//a customer can change email
   private JButton setAdmin;//an admin can set another user as admin but not de- admin them
   private JButton editAuthor;//an admin can edit an existing author(or add that author if there is an existing book for them.)
   private JButton newAuthor;
   private JButton newEditor;
   private JButton editPublisher;// admin can exit an existing publisher(or add them if there is a book for them.)
   private JButton newPublisher;
   //button for adding new stock.
   //button for adding new item.
   //button for new dvd.
   //button for new books.
   
   private JButton loginButton;
   private JButton logoutButton;
   private JButton queryButton;
   private JButton queryAButton;
   private JButton queryPButton;
   private JButton querySButton;
   private JButton queryKBButton;
   private JButton queryCButton;
   private JButton queryDButton;
   private JButton queryGButton;
   private JButton queryKDButton;
   private JButton queryHButton;
   private JButton queryOButton;
   private JButton queryQButton;
  
   private JPanel loginPanel;
   private JPanel queryPanel;
   private JPanel selectionPanel;
   private JPanel selectionPanel2;
   private JPanel selectionPanel3;
   private JPanel selectionPanel35;
   private JPanel selectionPanel4;
   private JPanel selectionPanel5;

   private JTable table;
   private JScrollPane scroller;

   private Connection connection;

   public DatabaseGui()
   {
      setLayout(new FlowLayout());

      // create GUI componenets for login
	  addUser = new JButton("new user");
	  deleteUser = new JButton("delete user");
	  changeName = new JButton("change name");
	  changePass = new JButton("new pass");
	  changeEmail = new JButton("Change Email");
	  setAdmin = new JButton("Admin");
	  editAuthor = new JButton("Edit Author");
	  newAuthor = new JButton("New Author");
	  editPublisher = new JButton("Edit publisher");
	  newPublisher = new JButton("New Publisher");
	  
	  // Shop buttons
	  newcart = new JButton("New Cart");//create a new cart
	  addtocart = new JButton("Add Item"); // add stock item to cart
      removefromcart = new JButton("Remove Item"); //remove stock item from cart
      mycart = new JButton("Cart"); // what is in my cart
      dumpcart = new JButton("Destroy Cart"); // delete my cart
      purchase = new JButton("Checkout");// purchase the contents of my cart
      restock = new JButton("Restock");// an admin can restock an item
	  
      loginPanel = new JPanel();
      idLabel = new JLabel("User Id");
      pwdLabel = new JLabel("Password");
      idField = new JTextField(10);
      pwdField = new JPasswordField(10);

	  bookButton = new JButton("Books");
	  dvdButton  = new JButton("DVDs");
	  editButton = new JButton("Edit");
	  shopButton = new JButton("Shop");
	  
	  bookButton.setActionCommand("BOOK");
	  dvdButton.setActionCommand("DVD");
	  editButton.setActionCommand("EDIT");
	  shopButton.setActionCommand("SHOP");
	  
	  //edit buttons
	  addUser.setActionCommand("AUSER"); 
	  deleteUser.setActionCommand("DUSER");
	  changeName.setActionCommand("CNAME");
	  changePass.setActionCommand("CPASS");
	  changeEmail.setActionCommand("CEMAIL");
	  setAdmin.setActionCommand("SADM");
	  editAuthor.setActionCommand("EAUTH");
	  newAuthor.setActionCommand("NAUTH");
	  editPublisher.setActionCommand("EPUB");
	  newPublisher.setActionCommand("NPUB");
	 //shop buttons
	  newcart.setActionCommand("NCAR");//create a new cart
	  addtocart.setActionCommand("ACAR"); // add stock item to cart
      removefromcart.setActionCommand("RCAR"); //remove stock item from cart
      mycart.setActionCommand("MCAR"); // what is in my cart
      dumpcart.setActionCommand("DCAR"); // delete my cart
      purchase.setActionCommand("PUR");// purchase the contents of my cart
      restock.setActionCommand("RSTO");// an admin can restock an item
	  
	 //edit buttons
	  addUser.setEnabled(false); 
	  deleteUser.setEnabled(false);
	  changeName.setEnabled(false);
	  changePass.setEnabled(false);
	  changeEmail.setEnabled(false);
	  setAdmin.setEnabled(false);
	  editAuthor.setEnabled(false);
	  newAuthor.setEnabled(false);
	  editPublisher.setEnabled(false);
	  newPublisher.setEnabled(false);
	  
	  addUser.setVisible(false);
	  deleteUser.setVisible(false);
	  changeName.setVisible(false);
	  changePass.setVisible(false);
	  changeEmail.setVisible(false);
	  setAdmin.setVisible(false);
	  editAuthor.setVisible(false);
	  newAuthor.setVisible(false);
	  editPublisher.setVisible(false);
	  newPublisher.setVisible(false);
	  
	  newcart.setEnabled(false); 
	  addtocart.setEnabled(false); 
      removefromcart.setEnabled(false); 
      mycart.setEnabled(false); 
      dumpcart.setEnabled(false); 
      purchase.setEnabled(false); 
      restock.setEnabled(false); 
	  
	  newcart.setVisible(false);
	  addtocart.setVisible(false);
      removefromcart.setVisible(false);
      mycart.setVisible(false);
      dumpcart.setVisible(false);
      purchase.setVisible(false);
      restock.setVisible(false);
      
	  loginButton = new JButton("Login");
	  loginButton.setActionCommand("IN");
	  logoutButton = new JButton("Logout");
	  logoutButton.setActionCommand("OUT");
	  logoutButton.setEnabled(false);
	  logoutButton.setVisible(false);
      loginPanel.setLayout(new GridLayout(2,2,0,5));
      loginPanel.add(idLabel);
      loginPanel.add(idField);
      loginPanel.add(pwdLabel);
      loginPanel.add(pwdField);
      add(loginPanel);
      add(loginButton);
	  add(logoutButton);

      LoginHandler lhandler = new LoginHandler();
      loginButton.addActionListener(lhandler);
	  logoutButton.addActionListener(lhandler);

      // create GUI componenets for query
      queryPanel = new JPanel();
      queryField = new JTextField(20);
	  queryField.setEnabled(false);
	  queryField.setVisible(false);

      queryButton = new JButton("Submit");
      queryButton.setActionCommand("QUERY");
      queryButton.setEnabled(false);
	  queryButton.setVisible(false);

      queryAButton = new JButton("By Author");
      queryAButton.setActionCommand("AUTHOR");
      queryAButton.setEnabled(false);
	  queryAButton.setVisible(false);
	  
	  queryPButton = new JButton("By Publisher");
      queryPButton.setActionCommand("PUBLISHER");
      queryPButton.setEnabled(false);
	  queryPButton.setVisible(false);

	  querySButton = new JButton("By Subject");
      querySButton.setActionCommand("SUBJECT");
      querySButton.setEnabled(false);
	  querySButton.setVisible(false);

	  
	  queryKBButton = new JButton("By Book Title");
      queryKBButton.setActionCommand("KEYBOOK");
      queryKBButton.setEnabled(false);
	  queryKBButton.setVisible(false);

	  
	  queryCButton = new JButton("By Cast");
      queryCButton.setActionCommand("CAST");
      queryCButton.setEnabled(false);
	  queryCButton.setVisible(false);

	  
	  queryDButton = new JButton("By Director");
      queryDButton.setActionCommand("DIRECTOR");
      queryDButton.setEnabled(false);
	  queryDButton.setVisible(false);

	  
	  queryGButton = new JButton("By DVD Genre");
      queryGButton.setActionCommand("GENRE");
      queryGButton.setEnabled(false);
	  queryGButton.setVisible(false);

	  
	  queryKDButton = new JButton("By DVD title");
      queryKDButton.setActionCommand("KEYDVD");
      queryKDButton.setEnabled(false);
	  queryKDButton.setVisible(false);

	  
	  queryHButton = new JButton("History"); // this needs testing
      queryHButton.setActionCommand("HISTORY");
      queryHButton.setEnabled(false);
	  queryHButton.setVisible(false);

	  
	  queryOButton = new JButton("24/h Order");/// This needs testing
      queryOButton.setActionCommand("ORDER");
      queryOButton.setEnabled(false);
	  queryOButton.setVisible(false);

	  
	  queryQButton = new JButton("Sequel");
	  queryQButton.setActionCommand("SEQUEL");
	  queryQButton.setEnabled(false);
	  queryQButton.setVisible(false);
	  
	  bookButton.setEnabled(false);
	  bookButton.setVisible(false);
	  dvdButton.setEnabled(false);
	  dvdButton.setVisible(false);
	  editButton.setEnabled(false);
	  editButton.setVisible(false);
	  shopButton.setEnabled(false);
	  shopButton.setVisible(false);

	  
      
      queryPanel.setLayout(new BoxLayout(queryPanel,BoxLayout.X_AXIS));
      queryPanel.setBorder(BorderFactory.createTitledBorder("Search/Query"));
      queryPanel.add(queryField);
      queryPanel.add(queryButton);
	  
	  selectionPanel = new JPanel();// book
	  selectionPanel.setLayout(new FlowLayout());
	  selectionPanel.add(bookButton);
	  selectionPanel.add(queryAButton);
	  selectionPanel.add(queryPButton);
      selectionPanel.add(querySButton);
	  selectionPanel.add(queryKBButton);
	  
	  selectionPanel2 = new JPanel();// dvd
	  selectionPanel2.setLayout(new FlowLayout());
	  selectionPanel2.add(dvdButton);
	  selectionPanel2.add(queryCButton);
	  selectionPanel2.add(queryDButton);
	  selectionPanel2.add(queryGButton);
	  selectionPanel2.add(queryKDButton);
	  selectionPanel2.add(queryQButton);
	  
	  selectionPanel3 = new JPanel();// edit
	  selectionPanel3.setLayout(new FlowLayout());
	  selectionPanel3.add(editButton);
	  selectionPanel3.add(addUser); 
	  selectionPanel3.add(deleteUser);
	  selectionPanel3.add(changeName);
	  selectionPanel3.add(changePass);
	  selectionPanel3.add(changeEmail);
	  selectionPanel35= new JPanel();
	  selectionPanel35.setLayout(new FlowLayout());
	  selectionPanel35.add(setAdmin);
	  selectionPanel35.add(newAuthor);
	  selectionPanel35.add(editAuthor);
	  selectionPanel35.add(editPublisher);
	  selectionPanel35.add(newPublisher);
	  
	  selectionPanel4 = new JPanel();//shop
	  selectionPanel4.setLayout(new FlowLayout());
	  selectionPanel4.add(shopButton);
	  selectionPanel4.add(addtocart);
	  selectionPanel4.add(removefromcart);
      selectionPanel4.add(purchase);
      selectionPanel4.add(restock);
	  
	  selectionPanel5 = new JPanel();//cartmanagement
	  selectionPanel5.add(newcart);
	  selectionPanel5.add(mycart);
      selectionPanel5.add(dumpcart);
	 
	  selectionPanel5.add(queryHButton);
	  selectionPanel5.add(queryOButton);
	  
	  add(queryPanel);
	  add(selectionPanel);
	  add(selectionPanel2);
	  add(selectionPanel3); 
	  add(selectionPanel35);
	  add(selectionPanel4);
	  add(selectionPanel5);

      QueryHandler qhandler = new QueryHandler();
      queryButton.addActionListener(qhandler);
	  queryAButton.addActionListener(qhandler);
	  queryPButton.addActionListener(qhandler);
	  querySButton.addActionListener(qhandler);
	  queryKBButton.addActionListener(qhandler);
	  queryCButton.addActionListener(qhandler);
	  queryDButton.addActionListener(qhandler);
	  queryGButton.addActionListener(qhandler); 
	  queryKDButton.addActionListener(qhandler); 
	  queryHButton.addActionListener(qhandler); 
	  queryOButton.addActionListener(qhandler);
	  queryQButton.addActionListener(qhandler);
	  
	  addUser.addActionListener(qhandler); 
	  deleteUser.addActionListener(qhandler);
	  changeName.addActionListener(qhandler);
	  changePass.addActionListener(qhandler);
	  changeEmail.addActionListener(qhandler);
	  setAdmin.addActionListener(qhandler);
	  editAuthor.addActionListener(qhandler);
	  newAuthor.addActionListener(qhandler);
	  editPublisher.addActionListener(qhandler);
	  newPublisher.addActionListener(qhandler);
	  //SHOP BUTTONS
	  newcart.addActionListener(qhandler);
	  addtocart.addActionListener(qhandler);
      removefromcart.addActionListener(qhandler);
      mycart.addActionListener(qhandler);
      dumpcart.addActionListener(qhandler);
      purchase.addActionListener(qhandler);
      restock.addActionListener(qhandler);
	  
	  bookButton.addActionListener(lhandler);
	  dvdButton.addActionListener(lhandler);
	  editButton.addActionListener(lhandler);
	  shopButton.addActionListener(lhandler);
	  
      WindowHandler window = new WindowHandler();
      this.addWindowListener(window);
   }

   // inner class for handling login
   private class LoginHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
		if(e.getActionCommand().equals("IN"))
		{
         String id = idField.getText();
         char[] p = pwdField.getPassword();
         String pwd = new String(p);

         // JDBC driver name and database URL - for MySQL
        

         try {
			
				if(connected == false)
				{
				 String driver = "com.mysql.jdbc.Driver";
				 String url = "jdbc:mysql://jsigley.us.to:33645/database3";
				 // Load the JDBC driver to allow connection to the database
				 Class.forName( "com.mysql.jdbc.Driver" );
				 // establish connection to database
				 connection = DriverManager.getConnection(url, "jsinkkanen", "KongMing2501");
				 connected = true;
				}
				
				Statement st;
				ResultSet rs;
				String query;
				
				query = "Select admin_flag FROM User u Where u.pass LIKE \'" ;
				query += pwd;
				query += "\' AND u.name LIKE \'";
				query += id;
				query += "\';";
				System.out.println(query);
				try {
					st = connection.createStatement();
					rs = st.executeQuery(query);
						if(!rs.next()) {
							JOptionPane.showMessageDialog(null,"Incorrect username or password");
							return;
						}
						else
							admin  = rs.getInt("admin_flag");
							System.out.println(admin);
					}

				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Query error!", JOptionPane.ERROR_MESSAGE);
				}
			

          
			}
         catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load JDBC driver!");
            System.exit(1);
         }
         catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Access denied!");
			//ex.printStackTrace();
            return;
			}
			
			
				loginButton.setEnabled(false);
				loginButton.setVisible(false);
				logoutButton.setEnabled(true);
				logoutButton.setVisible(true);
					
				idField.setEnabled(false);
				pwdField.setEnabled(false);
				queryField.setEnabled(true);
				queryField.setVisible(true);
				loginButton.setVisible(false);

			//=======================================
				bookButton.setEnabled(true);
				bookButton.setVisible(true);
				dvdButton.setEnabled(true);
				dvdButton.setVisible(true);
				editButton.setEnabled(true);
				editButton.setVisible(true);
				shopButton.setEnabled(true);
				shopButton.setVisible(true);
				queryHButton.setVisible(true);
				queryHButton.setEnabled(true);
				if (admin == 0)
				{
					queryButton.setEnabled(false);
					queryButton.setVisible(false);
					queryOButton.setEnabled(false);
					queryOButton.setVisible(false);
				}
				else				
				{
					queryButton.setEnabled(true);
					queryButton.setVisible(true);
					queryOButton.setEnabled(true);
					queryOButton.setVisible(true);
				}
		}	
		if(e.getActionCommand().equals("OUT"))
		{
				loginButton.setEnabled(true);
				loginButton.setVisible(true);
				logoutButton.setEnabled(false);
				logoutButton.setVisible(false);
					
				bookButton.setEnabled(false);
				bookButton.setVisible(false);
				dvdButton.setEnabled(false);
				dvdButton.setVisible(false);
				editButton.setEnabled(false);
				editButton.setVisible(false);
				shopButton.setEnabled(false);
				shopButton.setVisible(false);
					
				idField.setEnabled(true);
				idField.setText("");
				pwdField.setEnabled(true);
				pwdField.setText("");
				queryField.setEnabled(false);
				queryField.setVisible(false);
				loginButton.setVisible(true);

			//=======================================
				queryButton.setVisible(false);
				queryAButton.setVisible(false);
				queryOButton.setVisible(false);
				queryPButton.setVisible(false);
				querySButton.setVisible(false);
				queryKBButton.setVisible(false);
				queryCButton.setVisible(false);
				queryDButton.setVisible(false);
				queryGButton.setVisible(false);
				queryKDButton.setVisible(false);
				queryHButton.setVisible(false);
				queryQButton.setVisible(false);
					
				queryButton.setEnabled(false);
				queryAButton.setEnabled(false);
				queryOButton.setEnabled(false);
				queryPButton.setEnabled(false);
				querySButton.setEnabled(false);
				queryKBButton.setEnabled(false);
				queryCButton.setEnabled(false);
				queryDButton.setEnabled(false);
				queryGButton.setEnabled(false);
				queryKDButton.setEnabled(false);
				queryHButton.setEnabled(false);
				queryQButton.setEnabled(false);
				queryOButton.setEnabled(false);
				queryOButton.setVisible(false);
			    addUser.setEnabled(false);
			    deleteUser.setEnabled(false);
			    changeName.setEnabled(false);
			    changePass.setEnabled(false);
			    changeEmail.setEnabled(false);
			    setAdmin.setEnabled(false);
			    editAuthor.setEnabled(false);
			    newAuthor.setEnabled(false);
			    editPublisher.setEnabled(false);
			    newPublisher.setEnabled(false);
			  
			    addUser.setVisible(false);
			    deleteUser.setVisible(false);  
    		    changeName.setVisible(false);
			    changePass.setVisible(false);
			    changeEmail.setVisible(false);
			    setAdmin.setVisible(false);
			    editAuthor.setVisible(false);
			    newAuthor.setVisible(false);
			    editPublisher.setVisible(false);
			    newPublisher.setVisible(false);
						  
				newcart.setEnabled(false); 
				addtocart.setEnabled(false); 
				removefromcart.setEnabled(false); 
				mycart.setEnabled(false); 
				dumpcart.setEnabled(false); 
				purchase.setEnabled(false); 
				restock.setEnabled(false); 
				  
				newcart.setVisible(false);
				addtocart.setVisible(false);
				removefromcart.setVisible(false);
				mycart.setVisible(false);
				dumpcart.setVisible(false);
				purchase.setVisible(false);
				restock.setVisible(false);
			}
		if(e.getActionCommand().equals("BOOK"))
		{
			if(bflag == false)
			{
				queryAButton.setVisible(true);
				queryPButton.setVisible(true);
				querySButton.setVisible(true);
				queryKBButton.setVisible(true);
				bflag = true;
				queryAButton.setEnabled(true);
				queryPButton.setEnabled(true);
				querySButton.setEnabled(true);
				queryKBButton.setEnabled(true);
			}	
			else
			{
				queryAButton.setVisible(false);
				queryPButton.setVisible(false);
				querySButton.setVisible(false);
				queryKBButton.setVisible(false);
				bflag = false;
				queryAButton.setEnabled(true);
				queryPButton.setEnabled(true);
				querySButton.setEnabled(true);
				queryKBButton.setEnabled(true);
					
			}
		}
		if(e.getActionCommand().equals("DVD"))
		{
			if(dflag == false)
			{
				dflag = true;
				queryCButton.setVisible(true);
				queryDButton.setVisible(true);
				queryGButton.setVisible(true);
				queryKDButton.setVisible(true);
				queryHButton.setVisible(true);
				queryQButton.setVisible(true);
				
				queryCButton.setEnabled(true);
				queryDButton.setEnabled(true);
				queryGButton.setEnabled(true);
				queryKDButton.setEnabled(true);
				queryHButton.setEnabled(true);
				queryQButton.setEnabled(true);
			}
			else
			{
				dflag = false;
				queryCButton.setVisible(false);
				queryDButton.setVisible(false);
				queryGButton.setVisible(false);
				queryKDButton.setVisible(false);
				queryHButton.setVisible(false);
				queryQButton.setVisible(false);
				
				queryCButton.setEnabled(false);
				queryDButton.setEnabled(false);
				queryGButton.setEnabled(false);
				queryKDButton.setEnabled(false);
				queryHButton.setEnabled(false);
				queryQButton.setEnabled(false);
			}
		}
		if(e.getActionCommand().equals("EDIT"))
		{
			if(eflag == false)
			{
				eflag = true;
				if(admin==0)
				{
					changeName.setVisible(true);
					changeName.setEnabled(true);
					changePass.setEnabled(true);
					changePass.setVisible(true);
					changeEmail.setEnabled(true);
					changeEmail.setVisible(true);
				}
				else
				{
					editPublisher.setEnabled(true);
					editPublisher.setVisible(true);
					addUser.setEnabled(true);
					addUser.setVisible(true);
					deleteUser.setEnabled(true);
					deleteUser.setVisible(true);  
					setAdmin.setEnabled(true);
					setAdmin.setVisible(true);
					editAuthor.setEnabled(true);
					editAuthor.setVisible(true);
					newAuthor.setVisible(true);
					newAuthor.setEnabled(true);
					newPublisher.setVisible(true);
					newPublisher.setEnabled(true);
				}
			}
			else
			{
				if(admin == 0)
				{
					eflag = false;
					changeName.setEnabled(false);
					changeName.setVisible(false);
					changePass.setEnabled(false);
					changePass.setVisible(false);
					changeEmail.setEnabled(false);
					changeEmail.setVisible(false);
				}
				else
				{			 
					addUser.setEnabled(false);
					addUser.setVisible(false);
					deleteUser.setEnabled(false);
					deleteUser.setVisible(false);  
					setAdmin.setEnabled(false);
					setAdmin.setVisible(false);
					newAuthor.setEnabled(false);
					newAuthor.setVisible(false);
					editAuthor.setEnabled(false);
					editAuthor.setVisible(false);
					newPublisher.setEnabled(false);
					newPublisher.setVisible(false);
					editPublisher.setEnabled(false);
					editPublisher.setVisible(false);
				}
			}
		}
		if(e.getActionCommand().equals("SHOP"))
		{
			if(sflag == false)
			{
				sflag = true;
				newcart.setEnabled(true); 
				//addtocart.setEnabled(true); 
				//removefromcart.setEnabled(true); 
				//mycart.setEnabled(true); 
				//dumpcart.setEnabled(true); 
				//purchase.setEnabled(true); 
				  
				newcart.setVisible(true);
				addtocart.setVisible(true);
				removefromcart.setVisible(true);
				mycart.setVisible(true);
				dumpcart.setVisible(true);
				purchase.setVisible(true);
				if(admin == 1)
				{
					restock.setEnabled(true); 
					restock.setVisible(true);
				}
			}
			else
			{
				sflag = false;
				newcart.setEnabled(false); 
				addtocart.setEnabled(false); 
				removefromcart.setEnabled(false); 
				mycart.setEnabled(false); 
				dumpcart.setEnabled(false); 
				purchase.setEnabled(false); 
				restock.setEnabled(false); 
				  
				newcart.setVisible(false);
				addtocart.setVisible(false);
				removefromcart.setVisible(false);
				mycart.setVisible(false);
				dumpcart.setVisible(false);
				purchase.setVisible(false);
				restock.setVisible(false);
			}
		}
		
	  
	  }
   }

   // inner class for handling query
   private class QueryHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
		 String query = queryField.getText();
		 String query2 = queryField.getText();
		 String keyword;
			 //if(e.getActionCommand().equals("EAUTH"))
			 //if(e.getActionCommand().equals("EPUB"))
			
		 if (e.getActionCommand().equals("QUERY"))
        		query = queryField.getText();
         if (e.getActionCommand().equals("AUTHOR"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM Books  Where author LIKE  \'%" ;
				query += keyword;
				query += "%\' ORDER BY author;";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("PUBLISHER"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM Books k Where k.publisher LIKE  \'%" ;
				query += keyword;
				query += "%\'ORDER BY publisher;";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("SUBJECT"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM Books k Where k.Subject LIKE  \'%" ;
				query += keyword;
				query += "%\'ORDER BY Subject;";
				System.out.println(query);
			}	
		if (e.getActionCommand().equals("KEYBOOK"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM Books k Where k.title LIKE  \'%" ;
				query += keyword;
				query += "%\';";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("CAST"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM DVD Where cast LIKE  \'%" ;//title,year,price,stock FROM DVD, Member
				query += keyword;
				query += "%\';";
				System.out.println(query);
			}	
		if (e.getActionCommand().equals("DIRECTOR"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM DVD Where Director LIKE  \'%" ;//from memeber / dvd
				query += keyword;
				query += "%\' ORDER BY Director;";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("GENRE"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM DVD Where Genre LIKE  \'%" ;
				query += keyword;
				query += "%\'GROUP BY Genre;";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("KEYDVD"))
         	{	
				keyword = queryField.getText();
				query = "Select * FROM DVD  Where title LIKE  \'%" ;
				query += keyword;
				query += "%\';";
				System.out.println(query);
			}
		if (e.getActionCommand().equals("SEQUEL"))//does not search by keyword
         	{	
				query = "Select * FROM DVD  Where sequel <> 'NULL'" ;
				System.out.println(query);
			}
		if (e.getActionCommand().equals("HISTORY"))
         	{	
				if(admin == 1)
				{
					keyword = queryField.getText();
					query = "Select c.order_number,c.stock,c.quantity,c.price,cc.ID  FROM Contains c, Cart cc Where cc.ID LIKE  \'%" ;
					query += keyword;
					query += "%\' AND c.order_number = cc.order_number ORDER BY c.order_number;";
					
					
					if (keyword.equals(""))
						query = "SELECT c.order_number,c.stock,c.quantity,c.price,cc.ID FROM Contains c, Cart cc Where c.order_number = cc.order_number ORDER BY c.order_number";
					System.out.println(query);
					
				}
				else
				{
					query = "Select c.order_number,c.stock,c.quantity,c.price,cc.ID FROM Contains c, Cart cc, User u  Where u.name LIKE  \'%" ;
					query += idField.getText();
					query += "%\' AND c.order_number = cc.order_number AND u.ID = cc.ID ORDER BY c.order_number;";
					System.out.println(query);
				}
				
			}
//===================================================================================================================== THIS WILL PROBABLY NOT WORK!!!!			
		if (e.getActionCommand().equals("ORDER"))
         	{	
				query = "Select c.stock, c.quantity, c.price, p.date FROM Contains c, Purchases p Where c.order_number = p.order_number ORDER BY p.date desc";
				System.out.println(query);
			}	
			
         Statement statement;
         ResultSet resultSet;
         try {
            statement = connection.createStatement();
		 		 if(e.getActionCommand().equals("NAUTH"))
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JLabel label1 = new JLabel("Name");
					JLabel label2 = new JLabel("address");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					int result = JOptionPane.showConfirmDialog(null,panel,"Author",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into author (author,address) values('this','that');
						query = "INSERT INTO Author (author,address) VALUES (\"";
						query += field1.getText();
						query += "\",\"";
						query += field2.getText();
						query += "\");";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from Author";
						
					}
					else
					{
						System.out.println("canceled");
					}
				}
				if(e.getActionCommand().equals("CPASS"))
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("New Password");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					int result = JOptionPane.showConfirmDialog(null,panel,"Password Change",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						
						query = "UPDATE User SET pass =\'";
						query += field1.getText();
						query += "\' WHERE name = \'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "SELECT * FROM User WHERE name =\'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						
					}
					else
					{
						System.out.println("canceled");
						query = "SELECT * FROM User WHERE name =\"";
						query += idField.getText();
						query += "\";";
					}
				}	
				if(e.getActionCommand().equals("SADM"))
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("Promote user");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					int result = JOptionPane.showConfirmDialog(null,panel,"Promote User to ADMIN",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						
						query = "UPDATE User SET admin_flag = 1";
						query += " WHERE name = \"";
						query += field1.getText();
						query += "\";";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "SELECT * FROM User;";
					
						System.out.println(query);
						
					}
					else
					{
						System.out.println("canceled");
						query = "SELECT * FROM User;";
					}
				}					
				if(e.getActionCommand().equals("CEMAIL"))
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("New Email");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					int result = JOptionPane.showConfirmDialog(null,panel,"Email Change",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						
						query = "UPDATE User SET email =\'";
						query += field1.getText();
						query += "\' WHERE name = \'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "SELECT * FROM User WHERE name =\'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						
					}
					else
					{
						System.out.println("canceled");
						query = "SELECT * FROM User WHERE name =\'";
						query += idField.getText();
						query += "\';";
					}
				}	
				if(e.getActionCommand().equals("CNAME"))
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("New Username");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					int result = JOptionPane.showConfirmDialog(null,panel,"Name Change",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						
						query = "UPDATE User SET name =\'";
						query += field1.getText();
						query += "\' WHERE name = \'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "SELECT * FROM User WHERE name =\'";
						query += field1.getText();
						query += "\';";
						System.out.println(query);
						
					}
					else
					{
						System.out.println("canceled");
						query = "SELECT * FROM User WHERE name =\'";
						query += idField.getText();
						query += "\';";
					}
				}	
				if(e.getActionCommand().equals("AUSER"))
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JTextField field3 = new JTextField();
					JTextField field4 = new JTextField();
					JTextField field5 = new JTextField();
					JLabel label1 = new JLabel("ID(if too big will default):");
					JLabel label2 = new JLabel("Email:");
					JLabel label3 = new JLabel("Pass:");
					JLabel label4 = new JLabel("Name:");
					JLabel label5 = new JLabel("Admin(0/1)");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					panel.add(label3);
					panel.add(field3);
					panel.add(label4);
					panel.add(field4);
					panel.add(label5);
					panel.add(field5);
					int result = JOptionPane.showConfirmDialog(null,panel,"New User",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into User (ID,email,pass,name,admin_flag) values('this','that','these','those','stuff');
						query = "INSERT INTO User (ID,email,pass,name,admin_flag) VALUES (\"";
						query += field1.getText();
						query += "\",\"";
						query += field2.getText();
						query += "\",\"";
						query += field3.getText();
						query += "\",\"";
						query += field4.getText();
						query += "\",\"";
						query += field5.getText();
						query += "\");";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from User";
						
					}
					else
					{
						System.out.println("canceled");
					}
				}
				if(e.getActionCommand().equals("DUSER"))
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("Name");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
				
					int result = JOptionPane.showConfirmDialog(null,panel,"Delete User",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into author (author,address) values('this','that');
						query = "DELETE FROM User WHERE name LIKE \"";
						query += field1.getText();
						query += "\";";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from User";
						
					}
					else
					{
						System.out.println("canceled");
					}
				}	
				if(e.getActionCommand().equals("NPUB"))
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JTextField field3 = new JTextField();
					JTextField field4 = new JTextField();
					JLabel label1 = new JLabel("publisher");
					JLabel label2 = new JLabel("address");
					JLabel label3 = new JLabel("telephone");
					JLabel label4 = new JLabel("url");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					panel.add(label3);
					panel.add(field3);
					panel.add(label4);
					panel.add(field4);
					int result = JOptionPane.showConfirmDialog(null,panel,"Publisher",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into publisher (publisher,address,telephone,url) values('this','that','these','those');
						query = "INSERT INTO Publisher (publisher,address,telephone,url) VALUES (\"";
						query += field1.getText();
						query += "\",\"";
						query += field2.getText();
						query += "\",\"";
						query += field3.getText();
						query += "\",\"";
						query += field4.getText();
						query += "\");";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from Publisher";
						
					}
					else
					{
						query = "select * from Publisher";
						System.out.println("canceled");
					}
				}				
				if(e.getActionCommand().equals("EPUB"))
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JTextField field3 = new JTextField();
					JTextField field4 = new JTextField();
					JTextField field0 = new JTextField();
					JLabel label0 = new JLabel("target");
					JLabel label1 = new JLabel("publisher");
					JLabel label2 = new JLabel("address");
					JLabel label3 = new JLabel("telephone");
					JLabel label4 = new JLabel("url");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label0);
					panel.add(field0);
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					panel.add(label3);
					panel.add(field3);
					panel.add(label4);
					panel.add(field4);
					int result = JOptionPane.showConfirmDialog(null,panel,"Publisher Edit",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into publisher (publisher,address,telephone,url) values('this','that','these','those');
						query = "UPDATE Publisher SET publisher = \'";
						query += field1.getText();
						query += "\', address =\'";
						query += field2.getText();
						query += "\', telephone = \'";
						query += field3.getText();
						query += "\', url = \'";
						query += field4.getText();
						query += "\' WHERE publisher LIKE \'";
						query += field0.getText();
						query += "\';";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from Publisher";
						
					}
					else
					{
						query = "select * from Publisher";
						System.out.println("canceled");
					}
				}
				if(e.getActionCommand().equals("EAUTH"))
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JTextField field3 = new JTextField();
					JTextField field4 = new JTextField();
					JTextField field0 = new JTextField();
					JLabel label0 = new JLabel("target");
					JLabel label1 = new JLabel("author");
					JLabel label2 = new JLabel("address");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label0);
					panel.add(field0);
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					int result = JOptionPane.showConfirmDialog(null,panel,"Author Edit",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into publisher (publisher,address,telephone,url) values('this','that','these','those');
						query = "UPDATE Author SET author = \'";
						query += field1.getText();
						query += "\', address =\'";
						query += field2.getText();
						query += "\' WHERE author LIKE \'";
						query += field0.getText();
						query += "\';";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "select * from Author";
						
					}
					else
					{
						query = "select * from Author";
						System.out.println("canceled");
					}
				}
				if(e.getActionCommand().equals("NCAR"))//create a new cart
				{
						// load the user id for all future transactions
						query = "SELECT ID FROM User WHERE  name LIKE \'";
						query += idField.getText();
						query += "\';";
						System.out.println(query);
						resultSet = statement.executeQuery(query);
						if(!resultSet.next()) 
						{
						   JOptionPane.showMessageDialog(null,"Could not find userID");
						   return;
						}
						else
						{
							//Retrieve current user ID
							currentID  = resultSet.getInt("ID");
							System.out.println("User is:" + currentID);
						}
						//get the currently highest order number
						
						query = "SELECT order_number FROM Cart WHERE order_number =(SELECT MAX(order_number)FROM Cart);";
						System.out.println(query);
						resultSet = statement.executeQuery(query);
						if(!resultSet.next()) 
						{
						   JOptionPane.showMessageDialog(null,"Cannot find maximum order number");
						   return;
						}
						else
						{
							//Retrieve new order number
							orderNum  = resultSet.getInt("order_number");
							System.out.println("Maximum is:" + orderNum);
							orderNum++;
						}
						query = "INSERT INTO Cart (ID,order_number) VALUES (\"";
						query += currentID;
						query += "\",\"";
						query += orderNum;
						query += "\");";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "SELECT * FROM Cart WHERE ID =";
						query += currentID;
						query += ";";
						
						newcart.setEnabled(false); 
						addtocart.setEnabled(true); 
						removefromcart.setEnabled(true); 
						mycart.setEnabled(true); 
						 
						//query = "select * from Author";
				}
				if(e.getActionCommand().equals("ACAR")) // add stock item to cart
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JLabel label1 = new JLabel("Item ID");
					JLabel label2 = new JLabel("quantity");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					int result = JOptionPane.showConfirmDialog(null,panel,"Adding item to cart",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into publisher (publisher,address,telephone,url) values('this','that','these','those');
						query = "INSERT INTO Contains (order_number,stock,quantity) VALUES (\"";
						query += orderNum;
						query += "\",\"";
						query2 = "SELECT quantity FROM Item WHERE stock =";
						if(!field1.getText().equals("") )
						{
							query2 += field1.getText();
							query += field1.getText();
						}
						query += "\",\"";
						query2+= ";";
						System.out.println(query2);
						resultSet = statement.executeQuery(query2);
						while(resultSet.next())
							{
								int x = resultSet.getInt("quantity");
								if(x - Integer.parseInt(field2.getText())<0)
								{
									JOptionPane.showMessageDialog(null, null, "Not enough in stock", JOptionPane.ERROR_MESSAGE);
									query = "";
								}
									
							}
						if(!field2.getText().equals("") )
						{
							query += field2.getText();
						}
						query += "\");";
						System.out.println(query);
						statement.executeUpdate(query);
						dumpcart.setEnabled(true); 
						purchase.setEnabled(true); 
						query = "select * from Contains WHERE order_number =";
						query += orderNum;
						query += ";";
						
						
					}
					else
					{
						query = "select * from Contains WHERE order_number =";
						query += orderNum;
						query += ";";
						System.out.println("canceled");
					}
						
				}
				if(e.getActionCommand().equals("RCAR")) //remove stock item from cart
				{
					JTextField field1 = new JTextField();
					JLabel label1 = new JLabel("Item ID");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					int result = JOptionPane.showConfirmDialog(null,panel,"Removing all stock cart",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						//insert into publisher (publisher,address,telephone,url) values('this','that','these','those');
						query = "DELETE FROM Contains WHERE stock =";
						query += field1.getText();
						query += " AND order_number = ";
						query+= orderNum;
						query+= ";";
						System.out.println(query);
						statement.executeUpdate(query);
						
						query = "select * from Contains WHERE order_number =";
						query += orderNum;
						query += ";";
						
						
					}
					else
					{
						query = "select * from Contains WHERE order_number =";
						query += orderNum;
						query += ";";
						System.out.println("canceled");
					}
						
				}
				if(e.getActionCommand().equals("MCAR")) // what is in my cart
				{
						query = "select * from Contains WHERE order_number =";
						query += orderNum;
						query += ";";
				}
				if(e.getActionCommand().equals("DCAR")) // delete my cart
				{
				
						query = "DELETE FROM Contains WHERE order_number = ";
						query+= orderNum;
						query+= ";";
						System.out.println(query);
						statement.executeUpdate(query);
						query = "Select * From Item";
				}
				if(e.getActionCommand().equals("PUR"))// purchase the contents of my cart
				{
					query = "INSERT INTO Purchases (order_number,ID) VALUES (";
					query +=  orderNum;
					query += ",";
					query += currentID;
					query += ");";
					statement.executeUpdate(query);
					
					dumpcart.setEnabled(false); 
					purchase.setEnabled(false); 
					newcart.setEnabled(true); 
					query = "Select * From Item";
				}
				if(e.getActionCommand().equals("RSTO"))// an admin can restock an item
				{
					JTextField field1 = new JTextField();
					JTextField field2 = new JTextField();
					JLabel label1 = new JLabel("Item ID");
					JLabel label2 = new JLabel("quantity");
					JPanel panel= new JPanel(new GridLayout(0,1));
					panel.add(label1);
					panel.add(field1);
					panel.add(label2);
					panel.add(field2);
					int result = JOptionPane.showConfirmDialog(null,panel,"Restock item",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result== JOptionPane.OK_OPTION)
					{
						query = "UPDATE Item ";
						query+="SET quantity =";
						query += field2.getText();
						query+= " WHERE stock = ";
						query += field1.getText();
					}
					else
					{
						System.out.println("canceled");
					}
				}
			resultSet = statement.executeQuery(query);

			
            //If there are no records, display a message
            if(!resultSet.next()) {
               JOptionPane.showMessageDialog(null,"No records found!");
               return;
            }
            else {
               // columnNames holds the column names of the query result
               Vector<Object> columnNames = new Vector<Object>();

               // rows is a vector of vectors, each vector is a vector of
               // values representing a certain row of the query result
               Vector<Object> rows = new Vector<Object>();

               // get column headers
               ResultSetMetaData metaData = resultSet.getMetaData();

               for(int i = 1; i <= metaData.getColumnCount(); ++i)
                  columnNames.addElement(metaData.getColumnName(i));

               // get row data
               do {
                  Vector<Object> currentRow = new Vector<Object>();
                  for(int i = 1; i <= metaData.getColumnCount(); ++i)
                     currentRow.addElement(resultSet.getObject(i));
                  rows.addElement(currentRow);
               } while(resultSet.next()); //moves cursor to next record

               if(scroller!=null)
                  getContentPane().remove(scroller);

               // display table with ResultSet contents
               table = new JTable(rows, columnNames);
               table.setPreferredScrollableViewportSize(new Dimension(500, 10*table.getRowHeight()));
			   table.setEnabled(false);
               //table.doLayout();

               scroller = new JScrollPane(table);
               getContentPane().add(scroller);
               validate();
            }

            statement.close();
         }

         catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Query error!", JOptionPane.ERROR_MESSAGE);
         }
      }
   }

   // inner class for handling window event
   private class WindowHandler extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         try {
            if(connection!=null)
               connection.close();
         }
         catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to disconnect!");
         }
         System.exit(0);
      }
   }
   public static void main(String args[])
   {
      DatabaseGui db = new DatabaseGui();
      db.setSize(600,600);
      db.setResizable(false);
      db.setTitle("Accessing a Database using a GUI");
      db.setVisible(true);

   }

}

/* CODE DUMP
  while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

*/
