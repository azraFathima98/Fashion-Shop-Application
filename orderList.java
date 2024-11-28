import javax.swing.*;

class orderList{
	private Order[] orderArray;
	private int nextIndex;
	private double loadFact;
	private int initSize;
	
	orderList(int initSize, double loadFact){
		orderArray=new Order[initSize];
		nextIndex=0;
		this.loadFact=loadFact;
		this.initSize=initSize;
	}
	
	public boolean add(int index, Order order){
		if(isValidIndex(index)){
			for (int i = nextIndex-1; i >=index; i--){
				orderArray[i+1]=orderArray[i];
			}
			orderArray[index]=order;
			nextIndex++; 
			return true; 
		}
		return false;
	}
	private boolean isValidIndex(int index){
		return index>=0 && index<nextIndex;
	}
	
	public boolean add(Order order){
		if(nextIndex>= orderArray.length){ //isFull()
			extendArray();
		}	
		orderArray[nextIndex]=order;
		nextIndex++;
		return true;
	}
	public void set(int index, Order order){
		orderArray[index]=order;
	}
	private void extendArray(){
		int size=orderArray.length+(int)(orderArray.length*loadFact);
		Order[] tempOrderArray=new Order[size];
		for (int i = 0; i < orderArray.length; i++){
			tempOrderArray[i]=orderArray[i];
		}
		orderArray=tempOrderArray;
	}
	
	public void printList(){
		System.out.print("[");
		for (int i = 0; i <nextIndex; i++){
			System.out.print(orderArray[i].toString()+", ");
		}
		System.out.println(nextIndex<=0 ? "empty]":"\b\b]");
	}
	public boolean isEmpty(){
		return nextIndex<=0;	
	}		
	public boolean remove(int index){
		if(isValidIndex(index)){
			for(int i=index; i<nextIndex-1; i++){
				orderArray[i]=orderArray[i+1];
			}
			nextIndex--;
			return true;
		}
		return false;
	}
	public int capacity(){
		return orderArray.length;
	}
	public int size(){
		return nextIndex;
	}
	public void clear(){
		orderArray=new Order[initSize];
		nextIndex=0;
	}
	public Order get(int index){
		if(isEmpty()){
			return null;
		}
		return orderArray[index];
	}
	public int search(Order order){
		for(int i=0; i<nextIndex; i++){
			if(orderArray[i].equals(order)){
				return i;
			}
		}
		return -1;
	}
	public Order[] toArray(){
		Order[] tempOrderArray=new Order[nextIndex];
		for (int i = 0; i < nextIndex; i++){
			tempOrderArray[i]=orderArray[i];
		}
		return tempOrderArray;
	}
	
//-----------------------------------------

	
	public int getOrderArraySize(){
		return orderArray.length;
	}
	
	// Order id generate
    public String generateOrderID() {
		if(getOrderArraySize()==0){
			return "ODR#00001";
		}else{
			int lastNumber=Integer.parseInt(orderArray[orderArray.length-1].getOrderId().substring(4));
			return String.format("ODR#%05d",lastNumber+1);
		}
	}

    // Best In Customers Sorting Part
    public Order[] bestInCusSort(){
        Order[] viewBestCustomers = new Order[orderArray.length];
        boolean [] equalPass = new boolean[orderArray.length];
        int count=0;
        
        for(int i=0; i<orderArray.length; i++){
            if(equalPass[i]){
                continue;
            }

            viewBestCustomers[count]=new Order();

            int tempqty = orderArray[i].getQuantity();
            double tempAmount = orderArray[i].getAmount();
            equalPass[i]=true;

            for(int j=i+1; j<orderArray.length; j++){
                if(orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())){
                    tempqty+=orderArray[j].getQuantity();
                    tempAmount+=orderArray[j].getAmount();
                    equalPass[j]=true;
                }                
            }
            String customerPhoneNumber = orderArray[i].getCustomerID();

            viewBestCustomers[count].setBestInCustomers(customerPhoneNumber, tempqty, tempAmount);            
            count++;
        }
               
        // Sorting loop in best in customer....
        for(int i=count-1; i>0; i--){           
            for(int j=0; j<i; j++){    
                if(viewBestCustomers[j] != null && viewBestCustomers[j+1] != null){
                    if(viewBestCustomers[j].getQuantity()!=0 && viewBestCustomers[j+1].getQuantity()!=0){
                        if(viewBestCustomers[j].getAmount()<viewBestCustomers[j+1].getAmount()){                   
                            Order swap = viewBestCustomers[j];
                            viewBestCustomers[j]=viewBestCustomers[j+1];
                            viewBestCustomers[j+1]=swap;                    
                        }
                    }

                }              
            }
        }
        return viewBestCustomers;

    }

    // All Customer
    public AllCustomers[] allCustomersReport(){
        AllCustomers[] allCustomerDetails = new AllCustomers[orderArray.length];
        
        for(int i=0; i<allCustomerDetails.length; i++){
            allCustomerDetails[i]=new AllCustomers();
        }

        boolean equalPass[] = new boolean[orderArray.length];
        for(int i=0; i<orderArray.length; i++){
            if(equalPass[i]){
                continue;
            }

            int tempMedium2=0;
            int tempXtraSmall2=0;
            int tempXtraXl2=0;
            int tempXtraLarge2=0;
            int tempSmall2=0;
            int tempLarge2=0;
            double tempAmount2=0;

            String customerPhoneNumber = orderArray[i].getCustomerID();
            
            allCustomerDetails[i].setPhoneNumber(customerPhoneNumber);

            if(orderArray[i].getSize().equals("M")){
                tempMedium2=orderArray[i].getQuantity();
                allCustomerDetails[i].setMedium(tempMedium2);

            }else if (orderArray[i].getSize().equals("XS")){
                tempXtraSmall2=orderArray[i].getQuantity();
                allCustomerDetails[i].setXtraSmall(tempXtraSmall2);

            }else if(orderArray[i].getSize().equals("XXL")){
                tempXtraXl2=orderArray[i].getQuantity();
                allCustomerDetails[i].setXtraXl(tempXtraXl2);

            }else if(orderArray[i].getSize().equals("XL")){
                tempXtraLarge2=orderArray[i].getQuantity();
                allCustomerDetails[i].setXtraLarge(tempXtraLarge2);

            }else if(orderArray[i].getSize().equals("S")){
                tempSmall2=orderArray[i].getQuantity();
                allCustomerDetails[i].setSmall(tempSmall2);

            }else if(orderArray[i].getSize().equals("L")){
                tempLarge2=orderArray[i].getQuantity();
                allCustomerDetails[i].setLarge(tempLarge2);
            }

            tempAmount2=orderArray[i].getAmount();  
            allCustomerDetails[i].setAmount(tempAmount2);           
            equalPass[i]=true;            
    
            for(int j=i+1; j<orderArray.length; j++){
                if(orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())){                                             
                allCustomerDetails[i].setPhoneNumber(customerPhoneNumber);

                if(orderArray[i].getSize().equals("M")){
                    tempMedium2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setMedium(tempMedium2);    
                }else if (orderArray[i].getSize().equals("XS")){
                    tempXtraSmall2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setXtraSmall(tempXtraSmall2);
                }else if(orderArray[i].getSize().equals("XXL")){
                    tempXtraXl2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setXtraXl(tempXtraXl2);
                }else if(orderArray[i].getSize().equals("XL")){
                    tempXtraLarge2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setXtraLarge(tempXtraLarge2);
                }else if(orderArray[i].getSize().equals("S")){
                    tempSmall2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setSmall(tempSmall2);
                }else if(orderArray[i].getSize().equals("L")){
                    tempLarge2=orderArray[i].getQuantity();
                    allCustomerDetails[i].setLarge(tempLarge2);
                }
    
                tempAmount2=orderArray[i].getAmount();  
                allCustomerDetails[i].setAmount(tempAmount2);           
                equalPass[i]=true;            
                }                
            }           
        } 
        return allCustomerDetails; 

    }

    // View Customers
    public Order[] viewCustomer(){
        Order[] viewCustomer = new Order[orderArray.length];
        boolean[] equalPass = new boolean [orderArray.length];
        int count=0;

        for(int i=0; i<orderArray.length; i++){
            if(equalPass[i]){
                continue;
            }

            viewCustomer[count]=new Order();

            int tempQty = orderArray[i].getQuantity();
            double tempAmount=orderArray[i].getAmount();
            equalPass[i]=true;

            for(int j=i+1; j<orderArray.length; j++){
                if(orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())){
                    tempQty+=orderArray[i].getQuantity();
                    tempAmount+=orderArray[i].getAmount();
                    equalPass[j]=true;
                }
            }

            String cusPhoneNumber = orderArray[i].getCustomerID();
            viewCustomer[count].setViewCustomer(cusPhoneNumber, tempQty, tempAmount);
            count++;
        }
        return viewCustomer;
    }

    // Sorting by Quantity
    public Sorting[] sortByQty(){
        Sorting [] sortingByQuantity = new Sorting[6];
        for(int i=0; i<sortingByQuantity.length; i++){
            sortingByQuantity[i]=new Sorting();
        }

        sortingByQuantity[0].setSize("M");
        sortingByQuantity[1].setSize("Xl");
        sortingByQuantity[2].setSize("XS");
        sortingByQuantity[3].setSize("S");
        sortingByQuantity[4].setSize("XXL");
        sortingByQuantity[5].setSize("L");

        int tempM4=0;
        int tempXs4=0;
        int tempXtraXl4=0;
        int tempXLarge4=0;
        int tempSmall4=0;
        int tempLarge4=0;

        int mtotal=0;
        int xstotal=0;
        int xtraxltotal=0;
        int xlargetotal=0;
        int smalltotal=0;
        int largetotal=0;

        sortingByQuantity[0].setQuantity(0);
        sortingByQuantity[1].setQuantity(0);
        sortingByQuantity[2].setQuantity(0);
        sortingByQuantity[3].setQuantity(0);
        sortingByQuantity[4].setQuantity(0);
        sortingByQuantity[5].setQuantity(0);

        for(int i=0; i<orderArray.length; i++){
            if(orderArray[i].getSize().equals("M")){
                tempM4+=orderArray[i].getQuantity();
                sortingByQuantity[0].setQuantity(tempM4);

            }else if (orderArray[i].getSize().equals("XL")){
                tempXLarge4+=orderArray[i].getQuantity();
                sortingByQuantity[1].setQuantity(tempXLarge4);

            }else if(orderArray[i].getSize().equals("XS")){
                tempXs4+=orderArray[i].getQuantity();
                sortingByQuantity[2].setQuantity(tempXs4);

            }else if(orderArray[i].getSize().equals("S")){
                tempSmall4+=orderArray[i].getQuantity();
                sortingByQuantity[3].setQuantity(tempSmall4);

            }else if(orderArray[i].getSize().equals("XXL")){
                tempXtraXl4+=orderArray[i].getQuantity();
                sortingByQuantity[4].setQuantity(tempXtraXl4);

            }else if(orderArray[i].getSize().equals("L")){
                tempLarge4+=orderArray[i].getQuantity();
                sortingByQuantity[5].setQuantity(tempLarge4);
            }

            mtotal=tempM4*900;
            xlargetotal=tempXLarge4*1100;
            xstotal=tempXs4*600;
            smalltotal=tempSmall4*800;
            xtraxltotal=tempXtraXl4*1200;           
            largetotal=tempLarge4*1000;

            sortingByQuantity[0].setAmount((double)mtotal);
            sortingByQuantity[1].setAmount((double)xlargetotal);
            sortingByQuantity[2].setAmount((double)xstotal);
            sortingByQuantity[3].setAmount((double)smalltotal);
            sortingByQuantity[4].setAmount((double)xtraxltotal);
            sortingByQuantity[5].setAmount((double)largetotal);
          
        }
        // sorting part for quantity
        for(int i=5; i>0; i--){
            for(int j=0; j<i; j++){
                if(sortingByQuantity[j].getQuantity()<sortingByQuantity[j+1].getQuantity()){
                    Sorting swap = sortingByQuantity[j];
                    sortingByQuantity[j]=sortingByQuantity[j+1];
                    sortingByQuantity[j+1]=swap;
                }
            }
        }
        return sortingByQuantity;
    }

    //Sorting by Amount
    public Sorting[] sortByAmount(){
        Sorting [] sortByAmount = new Sorting[6];
        for(int i=0; i<sortByAmount.length; i++){
            sortByAmount[i]=new Sorting();
        }

        sortByAmount[0].setSize("M");
        sortByAmount[1].setSize("XL");
        sortByAmount[2].setSize("XS");
        sortByAmount[3].setSize("S");
        sortByAmount[4].setSize("XXL");
        sortByAmount[5].setSize("L");

        int tempM4=0;
        int tempXs4=0;
        int tempXtraXl4=0;
        int tempXLarge4=0;
        int tempSmall4=0;
        int tempLarge4=0;

        int mtotal=0;
        int xstotal=0;
        int xtraxltotal=0;
        int xlargetotal=0;
        int smalltotal=0;
        int largetotal=0;

        sortByAmount[0].setQuantity(0);
        sortByAmount[1].setQuantity(0);
        sortByAmount[2].setQuantity(0);
        sortByAmount[3].setQuantity(0);
        sortByAmount[4].setQuantity(0);
        sortByAmount[5].setQuantity(0);

        for(int i=0; i<orderArray.length; i++){
            if(orderArray[i].getSize().equals("M")){
                tempM4+=orderArray[i].getQuantity();
                sortByAmount[0].setQuantity(tempM4);
            }else if (orderArray[i].getSize().equals("XL")){
                tempXLarge4+=orderArray[i].getQuantity();
                sortByAmount[1].setQuantity(tempXLarge4);
            }else if(orderArray[i].getSize().equals("XS")){
                tempXs4+=orderArray[i].getQuantity();
                sortByAmount[2].setQuantity(tempXs4);
            }else if(orderArray[i].getSize().equals("S")){
                tempSmall4+=orderArray[i].getQuantity();
                sortByAmount[3].setQuantity(tempSmall4);
            }else if(orderArray[i].getSize().equals("XXL")){
                tempXtraXl4+=orderArray[i].getQuantity();
                sortByAmount[4].setQuantity(tempXtraXl4);
            }else if(orderArray[i].getSize().equals("L")){
                tempLarge4+=orderArray[i].getQuantity();
                sortByAmount[5].setQuantity(tempLarge4);
            }

            mtotal=tempM4*900;
            xlargetotal=tempXLarge4*1100;
            xstotal=tempXs4*600;
            smalltotal=tempSmall4*800;
            xtraxltotal=tempXtraXl4*1200;           
            largetotal=tempLarge4*1000;

            sortByAmount[0].setAmount((double)mtotal);
            sortByAmount[1].setAmount((double)xlargetotal);
            sortByAmount[2].setAmount((double)xstotal);
            sortByAmount[3].setAmount((double)smalltotal);
            sortByAmount[4].setAmount((double)xtraxltotal);
            sortByAmount[5].setAmount((double)largetotal);
          
        }
        for(int i=5; i>0; i--){           
            for(int j=0; j<i; j++){              
                if(sortByAmount[j].getAmount()<sortByAmount[j+1].getAmount()){                   
                        Sorting swap = sortByAmount[j];
                        sortByAmount[j]=sortByAmount[j+1];
                        sortByAmount[j+1]=swap;                    
                }           
            }
        }
        return sortByAmount;
    }

    // Orders by Amount all orders print
    public Order[] allOrdersByAmount(){
        Order[] sortingByAmount = new Order[orderArray.length];
        for(int i=0; i<orderArray.length; i++){
            sortingByAmount[i]=orderArray[i];
        }        

        for(int i=orderArray.length-1; i>0; i--){
            for(int j=0; j<i; j++)
            if(sortingByAmount[j].getAmount()<sortingByAmount[j+1].getAmount()){
                Order swap=orderArray[j];
                sortingByAmount[j]=sortingByAmount[j+1];
                sortingByAmount[j+1]=swap;
            }
        }
        return sortingByAmount;

    }

    public Order[] getOrderObject(){
		Order[] tempOrderArray=new Order[orderArray.length];
		for (int i = 0; i < orderArray.length; i++){
			tempOrderArray[i]=orderArray[i];
		}
		return tempOrderArray;
	}

    public Order searchOrder(String orderId){
        for(int i=0; i<orderArray.length; i++){
            if(orderArray[i].getOrderId().equalsIgnoreCase(orderId)){
                return orderArray[i];
            }
        }
        return null;
    }

    public Order[] searchCustomerID(String custId) {
        int count = 0;
        for (Order order : orderArray) {
            if (order.getCustomerID().equalsIgnoreCase(custId)) {
                count++;
            }
        }
        if (count == 0) {
            return new Order[0];
        }
        Order[] foundOrders = new Order[count];
        int index = 0;
        for (Order order : orderArray) {
            if (order.getCustomerID().equalsIgnoreCase(custId)) {
                foundOrders[index++] = order;
            }
        }
        return foundOrders;
    }

    public boolean deleteOrder(Order order){
        int index=-1;
        for(int i=0; i<orderArray.length; i++){
            if(order.getOrderId().equalsIgnoreCase(orderArray[i].getOrderId())){
                index=i;
                break;
            }            
        }
        if(index==-1){
            return false;
        }else{
            Order[] tempCusDetails = new Order[orderArray.length-1];
            for(int i=0, j=0; i<orderArray.length; i++){
                if(i!=index){
                    tempCusDetails[j]=orderArray[i];
                    j++;
                }
            }
            orderArray=tempCusDetails;
            return true;
        }
    }

	// Phone Number Validation
	public boolean getPhoneNumber(String cusPhoneNumber) {
        if (cusPhoneNumber.length() != 10 || cusPhoneNumber.charAt(0) != '0') {
            return false;
        } else {
            return true;
        }
    }

	// Size Validation
	public boolean getSize(String tShirtSize) {
        tShirtSize = tShirtSize.toUpperCase();
        if (tShirtSize.equals("XS") || tShirtSize.equals("S") || tShirtSize.equals("M")
                || tShirtSize.equals("L") || tShirtSize.equals("XL")
                || tShirtSize.equals("XXL")) {
            return true;
        } else {
            return false;
        }

    }

	 // Quantiity Validation
	public boolean getQuantity(String qty){
		if (Integer.parseInt(qty) > 0) {
			return true;
		}else{
			return false;
		}
	}

	// Amount Calculation
    public double getAmount(String qty, String tShirtSize) {
        tShirtSize = tShirtSize.toUpperCase();

        double amount=0;
        int quantiity = Integer.parseInt(qty);
        switch (tShirtSize) {
            case "XS":
                amount = quantiity * 600;
                break;
            case "S":
                amount = quantiity * 800;
                break;
            case "M":
                amount = quantiity * 900;
                break;
            case "L":
                amount = quantiity * 1000;
                break;
            case "XL":
                amount = quantiity * 1100;
                break;
            case "XXL":
                amount = quantiity * 1200;
                break;
        }        
        return amount;
    }
	
	public boolean addOrder(Order order) {
        Order[] tempOrderArray = new Order[orderArray.length + 1];

        for (int i = 0; i < orderArray.length; i++) {
            tempOrderArray[i] = orderArray[i];
        }

        tempOrderArray[tempOrderArray.length-1] = order;
        orderArray = tempOrderArray;
        return true;
    }

    public void changeStatus(Order order){
        if (order.getStatus().equalsIgnoreCase("processing")) {
			statusOptionAlert1(order);
		}else if (order.getStatus().equalsIgnoreCase("Delivering")) {
            Object[] options = {"Delivered"};

            int choice = JOptionPane.showOptionDialog(null,
                "Please select the status", // Message
                "Status Options", // Title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null); // Default option is "Cancel"

            // Handle the user's choice
            switch (choice) {
                case 0:
                    order.setStatus("Delivered");
                    break;
                default:
                    System.out.println("Alert closed without selection");
                    break;    
            }
        }else {
            JOptionPane.showMessageDialog(null,"Can't Change the Status !");
        }
    }

    public void statusOptionAlert1(Order order){
		Object[] options = {"Delivering", "Delivered"};
        
        // Show the option dialog
        int choice = JOptionPane.showOptionDialog(null,
                "Please select the status", // Message
                "Status Options", // Title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null); // Default option is "Cancel"

        // Handle the user's choice
        switch (choice) {
            case 0:
				order.setStatus("Delivering");
                break;
            case 1:
                order.setStatus("Delivered");
                break;
            default:
                System.out.println("Alert closed without selection");
                break; 
        }
	}
    public void deleteConfimation(Order order){
        Object[] options = {"Yes", "No"};
        
        // Show the option dialog
        int choice = JOptionPane.showOptionDialog(null,
                "Do you want to delete this order ?", // Message
                "delete Confimation", // Title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null); // Default option is "Cancel"

        // Handle the user's choice
        switch (choice) {
            case 0:
				boolean isDelete = deleteOrder(order);
                if(isDelete){
                    JOptionPane.showMessageDialog(null,"Order Delete Succesfull","Delete Order",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 1:
                //Canceled
                break;
            default:
                System.out.println("Alert closed without selection");
                break; 
        }
    }
}
