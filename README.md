# Equalight
Image Brightness Equalizer and Colour Editor

Basically the idea is to run through a lot of pictures and create modified versions of all of them with a similar filter.


Filter Options:
  
  The first argument must be the directory name.

  -r <int>:
    Red value from 0-255 to say what the new average Red value should be for the images. (Defaults to 0)
  -g <int>:
    Green value from 0-255 to say what the new average Green value should be for the images. (Defaults to 0)
  -b <int>:
    Blue value from 0-255 to say what the new average Blue value should be for the images. (Defaults to 0)
  -a <int>:
    All value from 0-255 to say what the new average value should be for all RGB components for the images. If supplied, it         overrides the rgb flags. This filter sets the average brightness of all the images, while not changing the rgb proportions.

Note: program will output all files in .jpg format.
