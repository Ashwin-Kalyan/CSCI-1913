def is_sorted(pricebook):
    if len(pricebook) == 0:
        return True

    for i in range(1, len(pricebook)):
        if pricebook[i][1] < pricebook[i - 1][1]:
            return False
    
    return True

def price_average(pricebook):
    if len(pricebook) == 0:
        return 0.0
    
    total = 0

    for i in range(0, len(pricebook)):
        total += pricebook[i][0]

    avg = float(total) / float(len(pricebook))

    return avg

def unsorted_get(pricebook, name):
    for price, product in pricebook:
        if product == name:
            return price

    return None

def unsorted_put(pricebook, name, price):
    for i, (p, product) in enumerate(pricebook):
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
    for i, (p, product) in enumerate(pricebook):
        if product == name:
            pricebook[i] = (price, product)
            return
    # If the product is not found, find the correct position to insert
    for i, (p, product) in enumerate(pricebook):
        if product > name:
            pricebook.insert(i, (price, name))
            return
    pricebook.append((price, name))