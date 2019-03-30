
public class Grid {
	int size = 10;
	int board[][] = new int[size][size];
	Boat boats[] = new Boat[10];
	int nBoats ;
	public void initGrid() {
		for(int i=0; i< size;i++) 
			for(int j=0; j<size; j++)
				board[i][j] = -1;
		nBoats = 0;
	}
	
    public void showGrid(){
    	for(int i=65; i< size+65; i++) {
    		System.out.printf("\t%c",i);
    	}
        System.out.println();
        
        for(int row=0 ; row < size ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < size ; column++ ){
                  System.out.print("\t"+this.board[row][column]);
            	
            }                              
            System.out.println();
        }

    }
    
   
    public void addBoats(Boat b) {         					//change name to addBoat
    	boats[nBoats] = b;
    	nBoats++;
    	for(int i=0; i<b.length; i++) {
    		if(i<2)
    			this.board[b.positions[i][0]][b.positions[i][1]] = -2;    		
    		else {
    			if(b.verticle == false)
    				this.board[b.positions[0][0]+b.length-i][b.positions[0][1]] = -2;
    			else
    				this.board[b.positions[0][0]][b.positions[0][1]+b.length-i] = -2;
    		}
    	}
    }
    
    public boolean canAdd(Boat b) { //not finished
    	if(b.verticle == true) {
    		for(int i = b.positions[0][0]-1; i < 3; i++) {
    			for(int j= b.positions[0][1]-1; j<b.length+2; j++) {
    				if ( this.board[i][j] == -2)
    					return false;
    			}
    		}
    	}
    	else {
    		for(int i = b.positions[0][0]-1; i < b.length +2; i++) {
    			for(int j= b.positions[0][1]-1; j<3; j++) {
    				if ( this.board[i][j] == -2)
    					return false;
    			}
    		}
    	}
    	return true;
    } 
    public int checkShoot(int x, int y,Grid enemyGrid) { 	
    	if ( this.board[x][y] == -1) { 
    		this.board[x][y] = 0;
    		enemyGrid.board[x][y] = 0;
    		return 0; 
    	}
    	else if(this.board[x][y] == -2) { // there was a ship
    		this.board[x][y] = 1;
    		enemyGrid.board[x][y] = 1;
    		for(int i=0; i<nBoats; i++) {

    			if(boats[i].verticle == true) {
    				if( x == boats[i].positions[0][0] && y<= boats[i].positions[1][1] && y>= boats[i].positions[0][1] ) {
							if( boats[i].length == 1 ) {    		    							
								this.board[x][y] = 2;   
								enemyGrid.board[x][y] = 2;
								return 2;
							}
    						for(int y1 = boats[i].positions[0][1]; y1<=boats[i].positions[1][1]; y1++) {
    							if(this.board[x][y1] == 1) {

    								if(y1 == boats[i].positions[1][1]) { // means boat is sank,change all to 2
    									//boat.sank function
    		    						for(int y2 = boats[i].positions[0][1]; y2<=boats[i].positions[1][1]; y2++) {
    		    							this.board[x][y2] = 2;   
    		    							enemyGrid.board[x][y2] = 2;
    		    						}
    		    						return 2;
    								}
    							}
    							else {
    			        			return 1;
    							}
    								
    						}
    					}
    			}
    					
    			else {  // horizontal 				
    				if( y == boats[i].positions[0][1] && x<= boats[i].positions[1][0] && x>= boats[i].positions[0][0] ) {
						for(int x1 = boats[i].positions[0][0]; x1<=boats[i].positions[1][0]; x1++) {
							if(this.board[x1][y] == 1) {
								if(x1 == boats[i].positions[1][0]) { // means boat is sank,change all to 2
									//boat.sank function
		    						for(int x2 = boats[i].positions[0][0]; x2<=boats[i].positions[1][0]; x2++) {
		    							this.board[x2][y] = 2;   		    
		    							enemyGrid.board[x2][y] = 2;
		    						}
		    						return 2;
								}
							}
							else {
					    		return 1;
							}			
						}
					}    			
    			}
    			if(i == nBoats-1) {  // sth went wrong
        			return 1;
    			}
    		}
    				
    	}
    	return 0;
    }
}