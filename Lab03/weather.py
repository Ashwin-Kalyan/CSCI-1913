# Template file for Lab03 -- weather. By Peter Wang and Daniel Kluver
# Additions made by: Ashwin Kalyan

# Import Statements
import csv # imported for DictReader
import math # imported for

# provided functions -- one handles some tedious loading details, and the other can help make sure you know what you're
# working with -- it's worth reading both carefully, and trying to learn from what you're seeing.

def load(filename):
    """ load the CSV file by name, return list of dictionaries, each dictionary describes one row. of the file"""
    reader = csv.DictReader(open(filename), dialect='excel', skipinitialspace=True)
    return list(reader)

# provided function
def min_min_temp(file_list):
    """ The input is a list of dictionaries like would be returned by the load function. The output is the minimum temperature
        observed in the dataset. We are tacitly assuming that the min temperature for a day is always below the max temperature"""
    min_temp = math.inf
    for row in file_list:
        row_min_temp = float(row["Min_Temperature"])
        if row_min_temp < min_temp:
            min_temp = row_min_temp
    return min_temp

# Put your functions below this.

def F_to_C(f_temp):
    """
    Takes a Farenheit temperature and converts it to Celsius using the formula C = 5(F-32) / 9.
    
    Args:
        f_temp (float): The Farenheit temperature that needs to be converted to Celsius.

    Return:
        float: The Farenheit temperature converted to Celsius.
    """
    convert_to_C = (5 * (float(f_temp) - 32)) / 9
    return convert_to_C

def F_to_C_file(file_list):
    """
    Takes a list of weather data and converts all Farenheit temperatures within it to Celsius.

    Args:
        file_list (list): A list of dictionaries containing weather data such as temperature, date, percipitation, etc.
    Return:
        None.
    """
    for row in file_list:
        # Converts the Max_Temperature from Fahrenheit to Celsius in each row of weather data
        if 'Max_Temperature' in row:
            row['Max_Temperature'] = F_to_C(row['Max_Temperature'])
        
        # Converts the Min_Temperature from Fahrenheit to Celsius in each row of weather data
        if 'Min_Temperature' in row:
            row['Min_Temperature'] = F_to_C(row['Min_Temperature'])

def clean(file_list, column):
    """
    Processes and returns a new list of only "clean" rows of data, which are rows of data without the special values of "T", "M", "S", "A", and "".

    Args:
        file_list (list): A list of dictionaries containing weather data such as temperature, date, percipitation, etc.
        column (str): The name of the column that should be cleaned.
    Return:
        list: A modified copy of the input list that has the special values removed.
    """
    special_values = {"T", "M", "S", "A", ""}

    # Creates a new list to store the cleaned rows
    cleaned_list = []
    
    for row in file_list:
        # Checks if the value in the specified column of each row is NOT a special value
        if row.get(column, "") not in special_values:
            # If it's not a special value, then it adds the row to the cleaned list
            cleaned_list.append(row)
    
    return cleaned_list

def average(file_list, column):
    """
    Finds the mathematical average of all values in a specified column of file_list.

    Args:
        file_list (list): A list of dictionaries containing weather data such as temperature, date, percipitation, etc.
        column (str): The name of the column that should be averaged.

    Return:
        float: An average of all values in the specified column of file_list.
    """
    # If file_list is empty, return 0.0
    if len(file_list) == 0:
        return 0.0

    # If the cleaned list is empty, also return 0.0
    cleaned_list = clean(file_list, column)
    if len(cleaned_list) == 0:
        return 0.0

    # Tracks the sum of all values in file_list
    sum_avg = 0.0

    for row in cleaned_list:
        # Gets value in the column for each row
        column_value = row.get(column, "")

        # Ensure the value is not empty; if it isn't, then add it to the sum of all values
        if column_value:  
            sum_avg += float(column_value)

    avg = sum_avg / len(cleaned_list)
    return avg

def total_rain_by_year(file_list):
    """
    Extracts year and percipitation amount from file_list and appends that to a dictionary to show total percipitation by year.

    Args:
        file_list (list): A list of dictionaries containing weather data such as temperature, date, percipitation, etc.
    
    Return:
        dict: A dictionary containing the year as integers and the associated percipitation value as floats. 
    """
    # Dictionary to store the total precipitation for each year
    rain_by_year = {}
    
    # Special values to ignore
    special_values = {"T", "M", "S", "A", ""}
    
    for row in file_list:
        # Extract the date and precipitation values from each row
        date = row.get("Date", "")
        precipitation = row.get("Precipitation", "")
        
        # Skip rows with special values in the Precipitation column
        if precipitation in special_values:
            continue
        
        # Extract the year from the date (first 4 characters)
        year = int(date[:4])
        precipitation_float = float(precipitation)
        
        # Add the precipitation to the total for the corresponding year
        if year in rain_by_year:
            rain_by_year[year] += precipitation_float
        else:
            rain_by_year[year] = precipitation_float
    
    return rain_by_year


if __name__ == "__main__":
    # This code block shows how you could use this file to produce a complete report.
    local_file = load("original.csv")
    print(len(local_file), "rows loaded")
    F_to_C_file(local_file)
    print("The lowest observed temperature was", min_min_temp(local_file))
    print("The average high temperature was", average(local_file, "Max_Temperature"))
    total_rain = total_rain_by_year(local_file)
    print("Rain by year:", total_rain)