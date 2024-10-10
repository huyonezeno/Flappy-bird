# Bài tập lớn OOP - FlappyBird Game

Trong bài tập lớn này, mục tiêu khi viết một phiên bản Java mô phỏng lại game Flappy Bird chứa các yếu tố hướng đối tượng.

![](https://cdnmedia.baotintuc.vn/Upload/yTwlGtgJTRZkeJAfcpWR4g/files/2023/08/8C/040823-tro-choi-bird.jpg)

## Mô tả các đối tượng trong trò chơi

Nếu bạn đã từng chơi FlappyBird, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (Bird,Tube,Score,UserAction) và nhóm đối tượng tĩnh (Background,LeaderBoard).

- <img src="https://github.com/huyonezeno/Flappy-bird/blob/main/res/flappy_bird.png" alt="Flappy Bird Icon" width="24px" height="24px"> *Bird* là nhân vật duy nhất của trò chơi.Bird có thể di chuyển lên và xuống dựa vào tần suất click chuột của người chơi.*Bird* là nhân vật duy nhất của trò chơi.Bird có thể di chuyển lên và xuống dựa vào tần suất click chuột của người chơi.
- <img src="https://github.com/huyonezeno/Flappy-bird/blob/main/res/tube_up.webp" alt="Tube icon" width="14px" height="24px"> *Tube* là đối tượng không cố định, xuất hiện với chiều cao ngẫu nhiên ở nền trên và nền dưới của khu vực chơi
- <img src="https://github.com/huyonezeno/Flappy-bird/blob/main/res/Score.png" alt="score icon" width="28px" height="24px"> *Score* là đối tượng biểu thị điểm của người chơi trong suốt quá trình chơi.Khi Bird qua được một Tube thì Score sẽ tăng 1 đơn vị, điểm sẽ bị khóa khi Bird bị rơi hoặc va vào Tube
- <img src="<img src="https://github.com/huyonezeno/Flappy-bird/blob/main/res/Score.png" alt="useraction icon" width="28px" height="24px">" alt="score icon" width="28px" height="24px"> *UserAction* biểu thị những hoạt động của người chơi tương tác với trò chơi.
- *Background* là đối tượng tạo hiệu ứng cuộn nền nơi nền di chuyển từ phải sang trái cùng với các Tube để tạo cảm giác di chuyển của đối tượng Bird.
- *LeaderBoard* hiển thị điểm của 10 người chơi cao điểm nhất và được cập nhật liên tục khi có người chơi vượt qua đưuọc những người chơi trước nằm trong top 10.

## Mô tả game play, xử lí khi va chạm, tính điểm
- Trong một màn chơi, khi người chơi click lần đàu tiên sẽ khởi động game và nền bắt đầu di chuyển cùng với các Tube từ trái sang phải đến đối tượng Bird.
- Lúc này người chơi sẽ điều chỉnh tần suất click làm cho Bird bay lên sao cho tránh va phải các Tube và đi qua khe hở giữa các Tube mà không chạm vào các Tube.
- Khi người buông chuột và ko click , Bird sẽ rơi và người chơi sẽ thua hoặc đâm vào các Tube thì sẽ dừng trò chơi và xuất hiện hộp thoại có các tùy chọn **OK** và **SHARE** để bắt đầu ván mới hoặc chia sẻ với bạn bè về điểm cao mới của mình.
- Nếu người chơi lọt vào LeaderBoard,hộp thoại sẽ hiển thị vị trí của người chơi đó trong 10 người có điểm coa nhất được ghi nhận.
- Người chơi cũng có thể tạm dừng trò chơi giữa nếu thấy game quá khó!!!

## Mô tả chi tiết về project
[UML](https://drive.google.com/file/d/1qPJnqrg3PO47RNYh01_HYLADr3DAurY5/view?usp=drivesdk)
