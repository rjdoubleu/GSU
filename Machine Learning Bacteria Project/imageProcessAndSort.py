import shutil
import os
import cv2
import numpy as np
import glob

#Process the image and return it in grayscale
def imageProcessor( img ):

    scale_percent = 60 # percent of original size
    width = int(img.shape[1] * scale_percent / 100)
    height = int(img.shape[0] * scale_percent / 100)
    dim = (width, height)
    # resize image
    img = cv2.resize(img, dim, interpolation = cv2.INTER_AREA)
    
    #== Parameters           
    BLUR = 21
    CANNY_THRESH_1 = 10
    CANNY_THRESH_2 = 20
    MASK_DILATE_ITER = 3
    MASK_ERODE_ITER = 3
    MASK_COLOR = (0.0,0.0,0.0) # In BGR format

    #-- Read image
    #-- img = cv2.imread(image,1)
    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)


    #-- Edge detection 
    edges = cv2.Canny(gray, CANNY_THRESH_1, CANNY_THRESH_2)
    edges = cv2.dilate(edges, None)
    edges = cv2.erode(edges, None)

    #-- Find contours in edges, sort by area 
    contour_info = []
    _, contours, _ = cv2.findContours(edges, cv2.RETR_LIST, cv2.CHAIN_APPROX_NONE)
    for c in contours:
        contour_info.append((
            c,
            cv2.isContourConvex(c),
            cv2.contourArea(c),
        ))
    contour_info = sorted(contour_info, key=lambda c: c[2], reverse=True)
    max_contour = contour_info[0]

    #-- Create empty mask, draw filled polygon on it corresponding to largest contour ----
    # Mask is black, polygon is white
    mask = np.zeros(edges.shape)
    for c in contour_info:
        cv2.fillConvexPoly(mask, c[0], (255))

    #-- Smooth mask, then blur it
    mask = cv2.dilate(mask, None, iterations=MASK_DILATE_ITER)
    mask = cv2.erode(mask, None, iterations=MASK_ERODE_ITER)
    mask = cv2.GaussianBlur(mask, (BLUR, BLUR), 0)
    mask_stack = np.dstack([mask]*3)    # Create 3-channel alpha mask

    #-- Blend masked img into MASK_COLOR background
    mask_stack  = mask_stack.astype('float32') / 255.0         
    img         = img.astype('float32') / 255.0    
    masked = (mask_stack * img) + ((1-mask_stack) * MASK_COLOR)  
    masked = (masked * 255).astype('uint8')

    #-- Convert to Gray Scale and return
    gray_image = cv2.cvtColor(masked, cv2.COLOR_BGR2GRAY)
    return gray_image

#Iterate through the now processed and classified images and sort them
#into the test and train folders
def processedImageSort( directory ):
    os.chdir(cwd + directory)
    images = glob.glob(cwd + directory + "*.jpg")
    index = 1

    for image in images:
        image = image[image.find("_")+1:] #ignores PIL_ substring
        image = image[image.find("_")+1:] #ignores the processed_ substring
        strainID = int(image[image.find("-")+1:image.find("_")])
        if strainID in incomplete_3:
            limit = 3
        elif strainID == incomplete_2:
            limit = 2
        else:
            limit = 4

        if index < limit:
            shutil.move(cwd + directory + "processed_" + image, cwd + directory + "Train\\" + image)
        else:
            shutil.move(cwd + directory + "processed_" +image, cwd + directory + "Test\\" + image)
            index = 0
        index = index + 1


#Arrays of strainIDs of host, envir and incomplete
#13 is omitted from host because the images are corrupt
host = [1,2,3,5,7,9,11,14,28,33,35,37,38,39,40,47,48,49,50,51,55,66,69,71,73,75,79,80,81,82,
        86,92,101,109,111,113,115,116,128,140,141,142,143,144,146,147,148,149,150,151,152,153,
        154,155,156,157,158,159,160,162,165,167,168,169,170,171,172,174,175,200,213,226,257,260,
        261,262,263,264,267,268,269,270,275,276,277,280,292,293,294,298,300,304,305,307,308,310,
        311,312,313,314,317,331,333,337,338]

#15 is omitted from envir because the images are corrupt		
envir = [23,24,27,29,52,78,89,90,100,119,120,121,122,123,124,125,126,127,129,130,131,132,133,
        134,135,136,137,138,139,209,210,211,229,230,231,232,233,234,235,236,237,248,252,253,254,
        255,266,315,316,322,325,327,329,330,334,335,336,340,341,342,343,344,345,346,347,348,349]

#This is a list of all strains that contain 3 images
incomplete_3 = [47, 78, 86, 122, 124, 132, 210]

#strain 329 is the only strain that contains 2 images
incomplete_2 = 329

#Get and set Directory
cwd = os.getcwd()
os.chdir(cwd + "\\Images")

#Rename Outlier Files 
for index in range(1,5):
    os.rename('PIL-55a_3dayLBCR-' + str( index ) + '.jpg', 'PIL-55_3dayLBCR-'+ str( index ) + '.jpg')

#Create list of images
images = glob.glob("*.jpg")

#Create host folder
os.makedirs(cwd + "\\Host")

#Create envir folder
os.makedirs(cwd + "\\Envir")

#Iterate through the images that are classifiable (is host or envir)
#Proccess them and sort them into folders
for image in images:
    strainID = int(image[image.find("-")+1:image.find("_")])
    i = cv2.imread(image, 1)
    if strainID in host:
        os.chdir(cwd + "\\Host")
        im = imageProcessor( i )
        cv2.imwrite("processed_" + image, im)
    elif strainID in envir:
        os.chdir(cwd + "\\Envir")
        im = imageProcessor( i )
        cv2.imwrite("processed_"+image, im)
    os.chdir(cwd + "\\Images")


os.makedirs(cwd + "\\Host\\Train")
os.makedirs(cwd + "\\Host\\Test")
processedImageSort( "\\Host\\" )

os.makedirs(cwd + "\\Envir\\Train")
os.makedirs(cwd + "\\Envir\\Test")
processedImageSort( "\\Envir\\" )
print("Done!")