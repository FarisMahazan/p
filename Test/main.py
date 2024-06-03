from parcel_calculator import calculate_price
from parcel_calculator import save_parcel_data

# Get user input for parcel dimensions and weight
length = float(input("Enter parcel length in centimeters: "))
width = float(input("Enter parcel width in centimeters: "))
height = float(input("Enter parcel height in centimeters: "))
weight = float(input("Enter parcel weight in kilograms: "))

# Calculate parcel price using imported function
price = calculate_price(length, width, height, weight)

# Print parcel price to user
print("The price of your parcel is: $", price)


save_parcel_data(length, width, height, weight, price)
print("Your data have been saved")
