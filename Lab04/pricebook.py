# @Author Ashwin Kalyan

def is_sorted(pricebook):
    """
    Checks if a given pricebook's product names are sorted alphabetically.

    Args:
        pricebook (list): A list of tuples containing price and product name.
    Return:
        bool: True: it is sorted alphabetically, False: it isn't sorted alphabetically.
    """
    if len(pricebook) == 0:
        return True

    for i in range(1, len(pricebook)):
        # Checks if the current product name is lexicographically smaller (nonalphabetical order) than the previous product name
        if pricebook[i][1] < pricebook[i - 1][1]:
            return False  
    # If there are no products that are out of alphabetical order, pricebook is sorted
    return True

def price_average(pricebook):
    """
    Calculates the average of all product's prices within pricebook.

    Args:
        pricebook (list): A list of tuples containing price and product name.
    Return:
        float: The average of all prices in pricebook.
    """
    if len(pricebook) == 0:
        return 0.0
    # Tracks the sum of all values in pricebook
    total = 0

    for i in range(0, len(pricebook)):
        total += pricebook[i][0]

    avg = float(total) / float(len(pricebook))
    return avg

def unsorted_get(pricebook, name):
    """
    Uses linear search algorithm to check if the named product is in pricebook, then returns its price.

    Args:
        pricebook (list): An unsorted list of tuples containing price and product name.
        name (str): Name of the product that user wants to find in pricebook.
    Return:
        price (float): If product is found, return the price of the product.
    """
    for price, product in pricebook:
        if product == name:
            return price
    # If there are no matches between inputted name and the product namess in pricebook, nothing is returned
    return None

def unsorted_put(pricebook, name, price):
    for i, (list_price, product) in enumerate(pricebook):
        if product == name:
            pricebook[i] = (price, product)
            return
    pricebook.append((price, name))

def sorted_get(pricebook, name):
    low = 0
    high = len(pricebook) - 1

    while low <= high:
            mid = (low + high) // 2
            if pricebook[mid][1] == name:
                return pricebook[mid][0]
            elif pricebook[mid][1] < name:
                low = mid + 1
            else:
                high = mid - 1
    return None

def sorted_put(pricebook, name, price):
    for i, (list_price, product) in enumerate(pricebook):
        if product == name:
            pricebook[i] = (price, product)
            return
    # If the product is not found, find the correct position to insert
    for i, (p, product) in enumerate(pricebook):
        if product > name:
            pricebook.insert(i, (price, name))
            return
    pricebook.append((price, name))