def calculate_price(length, width, weight, height):
        volume = length * width * height 

        if weight <= 1:
            if volume <= 5000:
                return 3
            elif volume <= 10000:
                return 5
            else:
                return 7
        elif weight <= 5:
            if volume <= 5000:
                return 5
            elif volume <= 10000:
                return 7
            else:
                return 9
        else:
            if volume <= 5000:
                return 7
            elif volume <= 10000:
                return 9
            else:
                return 11

def save_parcel_data(length, width, height, weight, price):
    file_path = "C:\\Users\\user\\Desktop\\PSP\\SEM 5\\PYTHON\\Test\\kotak.txt"
    with open(file_path, 'a') as file:
        file.write(f"Length : {length} cm\n")
        file.write(f"Width : {width} cm\n")
        file.write(f"Height : {height} cm\n")
        file.write(f"Weight : {weight} kg\n")
        file.write(f"Price : RM{price}\n")
        file.write("\n")
            

        
