:just a quick impl. can help get real names of crypted files, but can not decrypt
:this util was tested on files, like
:email-iizomer@aol.com.ver-CL 1.2.0.0.id-JKKLMMNNOOOPPQQRRSSSSTTUUVVWXXXXYYZZ-09.11.2015 9@53@161619071.randomname-DFFGHIIJJJKKLMMNNOOOOPPQQRRSSS.TTU.cbf

:attributes:
:-virVersion - like "CL 1.2.0.0" from example above
:-fileExtension - file extension, in my example - .cbf
:-emailPrefix - "email-iizomer@aol.com" (exactly with "email-")
:-maxFileSize - file larger then specified value in bytes will not be scaned. file size depends on available RAM at your machine. Feel free to change -Xmx option if needed for big files
java -Xmx768m -jar FileNameUncrypter.jar -virVersion "CL 1.2.0.0" -fileExtension .cbf -emailPrefix email-iizomer@aol.com -maxFileSize 99999999
PAUSE