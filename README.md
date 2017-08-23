See class header comments to understand the problem. 

#### How to build

 Fork this repository and clone it to your local machine 

    mvn clean install
    
    This step should be successful before commiting your code - that means develop the code to fix all unit tests
    
    
    
######################################################################

    Design idea
######################################################################

    This idea of designing refrigerator is to efficiently utilize refrigerator space 
    while placing an item into refrigerator. When an item is taken out from refrigerator
    , there is an chance to re-arrange those items in refrigerator so that space in refrigerator
    could be untilized effectively.
    
    A shelf class is defined to hold the available space value.
    
    My inital thought to this design is to always find the largest available space for the placing item.
    The large item is only placed at large shelf. Either medium or small items always looks for the next 
    largest available space within shelves. So, creating a maxheap for available space is the data structure 
    for my initial thought. Every time that a new item is going to be placed into the refrigerator, 
    maxheap gives largest available shelf in refrigerator.  However, my inital design does not take advantage 
    of the remaining space of occupied shelf.
           In addition, I found that the first choice to place small item is to place at remaining space of 
    occupied large shelf. If no space is not found at large shelf, the small shelf is the second choice 
    to efficiently place small item. If no space is found at both shelves, the medium shelf is the final place. 
    The same logic is able to apply to medium item. The first choice of medium item is to look for resting 
    avilable space in large shelf. If no space is not found, the only choice to medium is to place at medium shelf.
    In order to achive my second idea, there are three maxheaps. First maxheap is created for large shelf called
    maxHeapLargeShelf. Second maxheap is created for medium shelf called maxHeapMediumShelf. 
    Third maxheap is created for small shelf called maxHeapSmallShelf.
    Everytime to place a new item into refrigerator, if the user looks for available space in large shelves,
    maxHeapLargeShelf needs to be checked. The same operations will be applying on maxHeapMediumShelf 
    and maxHeapSmallShelf.
            In addition to three maxheap, a link list is used to hold the all placed items in refrigerator 
    so that the user is able to easily track. The link list is called items
    To find a desired item, the user can search for items. If the desired item is found, the item will be taken out from
    either one of shelves.
            Next step is to arrage all items in refrigerator. The idea of re-arranging items is that any item
    in the items looks for any remaining available space in either large or medium shelves. If available space is
    found, the item is going to be moved out from original shelf and placed into found shelf.
    
    

