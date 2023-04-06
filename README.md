# facebook-chatbot-chatGPT

## Setting environment

- Truy cập https://developers.facebook.com/ tạo ứng dụng nhắn tin

- Vào menu Cài đặt -> Thông tin cơ bản
- Khóa bí mật của ứng dụng là giá trị thuộc tính trong file env.yaml
- Miền ứng dụng là url sau khi deploy

![This is an image](/image/basic_info.png)


- Vào menu Messenger -> Cài đặt:Trong phần Mã truy cập ấn Tạo mã, đây là giá trị thuộc tính page_access_token trong file env.yaml

![This is an image](/image/page_access_code.png)

- Trong phần Webhooks: điền URL gọi lại là url bên trên và /webhook
- Mã xác minh và thuộc tính verify_token trong file env.yaml điền gì cũng được nhưng phải giống nhau

![This is an image](/image/webhooks1.png)

- Thêm 2 thuộc tính như trong ảnh

![This is an image](/image/webhooks2.png)
