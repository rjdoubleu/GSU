import os
import requests
import glob

def pause():
	resp = input("Press the <ENTER> key to continue or" + 
				 "\nPress 'E' to switch to the Envir/Test directory." + 
				 "\nIf you are already in the Enivr/Test directory," +
				 "\nPress 'E' to exit the program...\n")
	if resp == "E" or resp == "e":
		return "e"

def runTests ( directory ):
	i = 1
	os.chdir(cwd + directory + "\\Test")
	images = glob.glob("*.jpg")
	for image in images:
		print("\nTest " + str(image) + " from the " + directory[1:] + "/Test directory")
		print("Image " + str(i) + " of " + str(len(images)) + " in the " + directory[1:] + "/Test directory.\n")
		data = {'file': open(image, 'rb'), 'modelId': ('', '312859ca-58f5-4c8a-885a-38eeda12d874')}
		response = requests.post(url, auth= requests.auth.HTTPBasicAuth('pJGC0XyquhYM1kD_c0lf5KBc4SAspyh-', ''), files=data)
		response = str(response.text)
		subM = response[response.find("probability") + 13:]
		sub1 = subM[:subM.find('}')]
		print( directory[1:] + " Probability: " + sub1 )
		subM = subM[subM.find("probability") + 13:]
		sub2 = subM[:subM.find('}')]
		if directory[1:] == "Host":
			print("Envir Probability: " + sub2)
		else:
			print("Host Probability: " + sub2)
		print("\n")
		i = i + 1
		resp = pause()
		if resp == "e":
			break;
		
url = 'https://app.nanonets.com/api/v2/ImageCategorization/LabelFile/'	
cwd = os.getcwd()
runTests ( "\\Host" )
runTests ( "\\Envir" )
print("Finsihed!")