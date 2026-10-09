# Lab 04 - Thanh toán với Strategy và ISP

## 1. Phân tích thiết kế ban đầu

Hàm `pay(String method, double amount)` dùng nhiều nhánh `if–else` để chọn cách thanh toán. Cách này dễ hiểu nhưng khi thêm phương thức mới, phải sửa `PaymentService`. Điều này chưa phù hợp với nguyên lý OCP (mở rộng chức năng mà hạn chế sửa code có sẵn).

Các cách thanh toán nằm chung trong một hàm nên khi logic phức tạp hơn sẽ khó quản lý và kiểm thử riêng. Dùng chuỗi để chọn phương thức cũng dễ nhập sai tên.

Code ban đầu chưa có interface nên chưa thể nói là vi phạm ISP. Khi thiết kế lại, ta tạo interface nhỏ để các lớp không phải cài đặt những hàm không cần thiết.

## 2. Thiết kế mới

- `PaymentStrategy`: interface chung, chỉ có hàm `pay(double amount)`.
- `CreditCardPayment`, `PayPalPayment`, `EWalletPayment`, `BankTransferPayment`: mỗi lớp thực hiện một cách thanh toán.
- `PaymentService`: giữ một Strategy và gọi hàm thanh toán của Strategy đó.
- `Main`: client minh họa bốn phương thức và việc đổi Strategy khi chạy.

**Strategy:** Tách từng cách thanh toán thành lớp riêng. Client truyền đối tượng thanh toán vào `PaymentService` và có thể đổi bằng `setPaymentStrategy()`.

**ISP:** Interface chỉ có hành vi thanh toán mà tất cả các lớp đều cần. Không đưa các hàm riêng như `loginPayPal()` hoặc `checkCardNumber()` vào interface chung.

Khi muốn thêm phương thức mới, chỉ cần tạo lớp cài đặt `PaymentStrategy` và sử dụng lớp đó ở client. Không cần thêm nhánh điều kiện trong `PaymentService`.

Ví dụ này mô phỏng thanh toán bằng cách in ra màn hình, chưa kết nối dịch vụ thanh toán thực tế. `PaymentService` kiểm tra phương thức không được null và số tiền phải là số hữu hạn lớn hơn 0.

## 3. Chạy chương trình

Mở PowerShell tại thư mục dự án. Cần có JDK để dùng `javac` và `java`.

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out src/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```

Kết quả mong đợi:

```text
Thanh toán bằng thẻ tín dụng: 500000.0
Thanh toán bằng PayPal: 200000.0
Thanh toán bằng ví điện tử: 100000.0
Thanh toán bằng chuyển khoản: 1000000.0
```
