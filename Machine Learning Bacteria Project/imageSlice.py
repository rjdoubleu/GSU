import numpy as np                                                                                                                                                                                                          
import sys
import cv2     

image = cv2.imread('PIL-3_3dayLBCR-2.jpg')
width, height, channels = image.shape

# mask (of course replace corners with yours)
mask = np.zeros(image.shape, dtype=np.uint8)
#np.array([[TopLeftCorner, BotRightCorner, BotLeftCorner 

# left top triangle 1
roi_corners = np.array([[(0,0), (width/2,height/2), (0,height)]], dtype=np.int32)
white = (255, 255, 255)
cv2.fillPoly(mask, roi_corners, white)

# apply the mask
masked_image = cv2.bitwise_and(image, mask)


#shrink the top
iii = 0
#the matrix sum of back is 0
while  not np.sum(masked_image[iii,:,:]):
        resized_top = masked_image[iii+1:,:,:]
        iii = iii + 1


#shrink the bottom
size_img = resized_top.shape
iii = size_img[0]
while not np.sum(resized_top[iii-2:iii-1,:,:]):
        resized_bottom = resized_top[0:iii-1,:,:]
        iii = iii - 1

#shrink the left
iii = 0
while  not np.sum(resized_bottom[:,iii,:]):
        resized_left = resized_bottom[:,iii+1:,:]
        iii = iii + 1

#shrink the right
size_img = resized_left.shape
iii = size_img[1]
print(iii)
while  not np.sum(resized_left[:,iii-2:iii-1,:]):
        resized_right = resized_left[:,0:iii-1:,:]
        iii = iii - 1


#display your handywork
cv2.imshow('masked image', resized_right)
cv2.waitKey()
cv2.destroyAllWindows()