# Git Commands - Người 2: Room Database + Model + DAO

## Phương pháp 1: Add các file cụ thể của Người 2 (Khuyến nghị)

```bash
# 1. Kiểm tra status 
git status

# 2. Add chỉ file của Người 2 (DAO, Model, Database)
git add app/src/main/java/**/AppDatabase.java
git add app/src/main/java/**/MovieDao.java
git add app/src/main/java/**/TheaterDao.java
git add app/src/main/java/**/ShowtimeDao.java
git add app/src/main/java/**/TicketDao.java
git add app/src/main/java/**/UserDao.java
git add app/src/main/java/**/User.java
git add app/src/main/java/**/Movie.java
git add app/src/main/java/**/Theater.java
git add app/src/main/java/**/Showtime.java
git add app/src/main/java/**/Ticket.java
git add app/src/main/java/**/ShowtimeDetail.java
git add app/src/main/java/**/TicketDetail.java

# 3. Xác nhận thay đổi
git commit -m "feat(person2): Add Room Database, Models and DAOs for Movies, Users, Theaters, Showtimes and Tickets"

# 4. Đẩy lên server
git push origin vu
```

## Phương pháp 2: Add tất cả rồi loại bỏ file người khác

```bash
# 1. Add tất cả file
git add .

# 2. Loại bỏ file của Người 1 (Activities)
git reset HEAD app/src/main/java/**/MainActivity.java
git reset HEAD app/src/main/java/**/LoginActivity.java
git reset HEAD app/src/main/java/**/SessionManager.java
git reset HEAD app/src/main/java/**/activity_main.xml
git reset HEAD app/src/main/java/**/activity_login.xml

# 3. Loại bỏ file của Người 3 (Movies/Theater Activities)
git reset HEAD app/src/main/java/**/MoviesActivity.java
git reset HEAD app/src/main/java/**/TheatersActivity.java
git reset HEAD app/src/main/java/**/TheaterMoviesActivity.java
git reset HEAD app/src/main/java/**/MovieShowtimesActivity.java
git reset HEAD app/src/main/java/**/ShowtimesActivity.java
git reset HEAD app/src/main/java/**/activity_list.xml
git reset HEAD app/src/main/java/**/DisplayCardAdapter.java
git reset HEAD app/src/main/java/**/DisplayItem.xml
git reset HEAD app/src/main/java/**/item_list_card.xml

# 4-5. Loại bỏ file của Người 4 + Người 5 (1 dòng)
git reset HEAD app/src/main/java/**/SeatSelectionActivity.java app/src/main/java/**/BookTicketActivity.java app/src/main/java/**/activity_seat_selection.xml app/src/main/java/**/activity_book_ticket.xml app/src/main/java/**/bg_seat_available.xml app/src/main/java/**/bg_seat_booked.xml app/src/main/java/**/TicketsActivity.java app/src/main/java/**/AndroidManifest.xml app/src/main/res/**/colors.xml app/src/main/res/**/themes.xml app/src/main/res/**/dimen.xml app/src/main/res/**/bg_screen_gradient.xml app/src/main/res/**/bg_card_soft.xml app/src/main/res/**/bg_item_card_green.xml app/src/main/res/**/ic_back.xml

# 6. Kiểm tra lại trước khi commit
git status

# 7. Xác nhận thay đổi
git commit -m "feat(person2): Add Room Database, Models and DAOs for Movies, Users, Theaters, Showtimes and Tickets"

# 8. Đẩy lên server
git push origin vu
```

## Phương pháp 3: Một dòng lệnh nhanh nhất

```bash
git add app/src/main/java/**/{AppDatabase,*Dao,User,Movie,Theater,Showtime,Ticket,ShowtimeDetail,TicketDetail}.java && git commit -m "feat(person2): Add Room Database, Models and DAOs" && git push origin vu
```



## Chi tiết lệnh

| Lệnh | Mô tả |
|------|-------|
| `git status` | Hiển thị danh sách file được sửa đổi |
| `git add app/src/main/java/**/{*Dao,*Model}.java` | Add file DAO và Model |
| `git reset HEAD <file>` | Loại bỏ file khỏi staging |
| `git commit -m "..."` | Lưu các thay đổi với thông điệp |
| `git push origin vu` | Đẩy lên branch vu trên remote |

## Các file của Người 2

✅ **DAO Layer:**
- AppDatabase.java
- MovieDao.java
- TheaterDao.java
- ShowtimeDao.java
- TicketDao.java
- UserDao.java

✅ **Model/Entity:**
- User.java
- Movie.java
- Theater.java
- Showtime.java
- Ticket.java
- ShowtimeDetail.java
- TicketDetail.java

## ⚠️ File KHÔNG phải của Người 2

❌ **Người 1 - Auth + Home:**
- MainActivity.java, LoginActivity.java, SessionManager.java
- activity_main.xml, activity_login.xml

❌ **Người 3 - Movie/Theater List:**
- MoviesActivity.java, TheatersActivity.java, TheaterMoviesActivity.java
- MovieShowtimesActivity.java, ShowtimesActivity.java
- DisplayCardAdapter.java, activity_list.xml, item_list_card.xml

❌ **Người 4 - Booking:**
- SeatSelectionActivity.java, BookTicketActivity.java
- activity_seat_selection.xml, activity_book_ticket.xml

❌ **Người 5 - UI/Themes:**
- TicketsActivity.java, AndroidManifest.xml
- colors.xml, themes.xml, gradients...

## 🚀 Khuyến nghị: Dùng Phương pháp 1

**Nhanh nhất & an toàn nhất** - chỉ add những file của bạn, không sợ thêm nhầm!

---

## 🔄 Chuyển sang branch khác nhanh chóng

### Cách 1: Checkout (cách cũ)

```bash
# Chuyển sang branch main
git checkout main

# Chuyển sang branch person1
git checkout person1

# Chuyển sang branch person2
git checkout person2
```

### Cách 2: Switch (cách mới - khuyến nghị)

```bash
# Chuyển sang branch main
git switch main

# Chuyển sang branch person1
git switch person1
```

### Cách 3: Xem tất cả branches rồi chọn

```bash
# Xem tất cả branches (local)
git branch

# Xem tất cả branches (kể cả remote)
git branch -a

# Chuyển sang branch bạn muốn
git checkout <tên_branch>
```

### Một dòng nhanh nhất

```bash
# Chuyển sang main
git checkout main

# Hoặc
git switch main
```

## 📋 Các lệnh chuyển branch thường dùng

| Lệnh | Mô tả |
|------|-------|
| `git checkout main` | Chuyển sang branch main (cách cũ) |
| `git switch main` | Chuyển sang branch main (cách mới) |
| `git branch` | Xem tất cả branches (local) |
| `git branch -a` | Xem tất cả branches (kể cả remote) |
| `git checkout -b new_branch` | Tạo branch mới và chuyển sang |
| `git switch -c new_branch` | Tạo branch mới và chuyển sang (cách mới) |

---

## 📜 Xem lịch sử Commit

### Cách 1: Xem tất cả commits (dạng rút gọn)

```bash
# Xem lịch sử commit (1 dòng mỗi commit)
git log --oneline

# Ví dụ output:
# a1f2e3c (HEAD -> main) Merge person2: Add Database and DAOs
# b2c3d4e Merge person1: Add Auth + Home
# c3d4e5f Initial commit
```

### Cách 2: Xem commit chi tiết

```bash
# Xem lịch sử commit với thông tin chi tiết
git log

# Hoặc xem N commit gần nhất
git log -n 5

# Xem commit của 1 người cụ thể
git log --author="Người 2"
```

### Cách 3: Xem commit với graph (dạng cây)

```bash
# Xem lịch sử dạng cây (dễ nhìn hơn)
git log --oneline --graph --all

# Ví dụ:
# * a1f2e3c (HEAD -> main) Merge person2
# |\
# | * 12345ab (person2) Add Database
# |/
# * b2c3d4e (person1) Add Auth
```

### Cách 4: Xem commit trong 1 khoảng thời gian

```bash
# Xem commit từ hôm qua
git log --since="1 day ago"

# Xem commit từ 1 tuần trước
git log --since="1 week ago"

# Xem commit trong khoảng thời gian cụ thể
git log --since="2026-03-20" --until="2026-03-27"
```

### Cách 5: Xem diff (thay đổi) trong commit

```bash
# Xem thay đổi trong commit cuối cùng
git show HEAD

# Xem thay đổi trong commit cụ thể (thay <commit_id>)
git show <commit_id>

# Xem diff giữa 2 commits
git diff <commit_id1> <commit_id2>
```

## 📋 Các lệnh xem commit thường dùng

| Lệnh | Mô tả |
|------|-------|
| `git log --oneline` | Xem lịch sử (rút gọn) |
| `git log -n 5` | Xem 5 commit gần nhất |
| `git log --graph --all` | Xem dạng cây |
| `git log --author="tên"` | Xem commit của 1 người |
| `git show HEAD` | Xem chi tiết commit cuối |
| `git log --since="1 day ago"` | Xem commit từ hôm qua |

---

## 📥 Lệnh PULL từ các branch khác

```bash
# Pull từ branch của Người 1 (Auth + Home)
git pull origin person1

# Pull từ branch của Người 3 (Movies/Theater List)
git pull origin person3

# Pull từ branch của Người 4 (Booking)
git pull origin person4

# Pull từ branch của Người 5 (UI/Themes)
git pull origin person5
```

## 🔄 Pull + Merge tất cả branches

```bash
# 1. Đảm bảo bạn ở branch chính (main hoặc master)
git checkout main

# 2. Pull tất cả branches
git pull origin person1 && git pull origin person3 && git pull origin person4 && git pull origin person5

# Hoặc từng cái một:
git pull origin person1
git pull origin person3
git pull origin person4
git pull origin person5

# 3. Đẩy lên server
git push origin main
```

## 📋 Danh sách các branches

| Branch | Cấu hình |
|--------|---------|
| `person1` | Auth + Home (MainActivity, LoginActivity, SessionManager) |
| `person2` | Room Database + Models + DAOs |
| `person3` | Movies/Theater List (Activities, Adapters) |
| `person4` | Seat Booking (SeatSelectionActivity, BookTicketActivity) |
| `person5` | UI/Themes (Colors, Gradients, Manifest) |
| `main` | Branch chính merge |

## ⚡ Một dòng để pull tất cả

```bash
git pull origin person1 && git pull origin person3 && git pull origin person4 && git pull origin person5 && git push origin main
```

---

## 🔀 Merge tất cả branches vào main

### Cách 1: Pull + Auto Merge (Đơn giản nhất)

```bash
# 1. Chuyển sang branch main
git checkout main

# 2. Pull và merge từng branch
git pull origin person1
git pull origin person3
git pull origin person4
git pull origin person5

# 3. Đẩy tất cả lên server
git push origin main
```

### Cách 2: Merge Explicit (An toàn hơn)

```bash
# 1. Chuyển sang branch main
git checkout main

# 2. Merge từng branch với thông điệp rõ ràng
git merge person1 -m "Merge person1: Auth + Home"
git merge person3 -m "Merge person3: Movies/Theater List"
git merge person4 -m "Merge person4: Seat Booking"
git merge person5 -m "Merge person5: UI/Themes"

# 3. Giải quyết xung đột nếu có (nếu cần)
git status

# 4. Đẩy lên server
git push origin main
```

### Cách 3: Merge tất cả 1 dòng

```bash
git checkout main && git pull origin person1 && git pull origin person3 && git pull origin person4 && git pull origin person5 && git push origin main
```

## ⚠️ Nếu có xung đột (Conflict)

```bash
# 1. Kiểm tra các file bị xung đột
git status

# 2. Mở file và chỉnh sửa xung đột (tìm dòng <<<<<<, ======, >>>>>> )
# Sau đó xóa các đánh dấu và giữ lại code đúng

# 3. Đánh dấu file đã resolve
git add <tên_file_bị_xung_đột>

# 4. Hoàn tất merge
git commit -m "Resolve merge conflict"
git push origin main

# HOẶC hủy merge nếu quá phức tạp:
git merge --abort
```

## 🔙 Hủy Pull / Hủy Merge

### Cách 1: Hủy merge đang trong quá trình

```bash
# Hủy ngay merge/pull hiện tại (nếu đang merge)
git merge --abort
```

### Cách 2: Hủy sau khi đã merge (Reset về commit trước)

```bash
# 1. Kiểm tra lịch sử
git log --oneline

# 2. Reset về commit trước khi merge (thay <commit_id> bằng ID commit)
git reset --hard <commit_id>

# Ví dụ: Hủy pull vừa rồi (quay về 1 commit trước)
git reset --hard HEAD~1

# 3. Push lại (nếu đã push lên server)
git push origin main --force
```

### Cách 3: Hủy bằng Revert (Tạo commit undo, không xóa lịch sử)

```bash
# 1. Xem lịch sử
git log --oneline

# 2. Revert commit merge (thay <commit_id> bằng ID commit merge)
git revert -m 1 <commit_id>

# 3. Push lên
git push origin main
```

## 📋 So sánh các cách hủy

| Lệnh | Ưu điểm | Nhược điểm |
|------|--------|-----------|
| `git merge --abort` | Nhanh, an toàn, không lộ lịch sử | Chỉ dùng khi đang merge |
| `git reset --hard` | Xóa hết, quay về trạng thái trước | Xóa lịch sử, cần --force push |
| `git revert` | Giữ lịch sử, rõ ràng | Tạo commit mới, phức tạp hơn |

## ⚡ Hủy nhanh nhất

```bash
# Nếu đang merge → hủy ngay
git merge --abort

# Nếu đã merge → quay lại
git reset --hard HEAD~1
git push origin main --force
```

## 📊 Chiến lược tốt nhất

| Cách | Ưu điểm | Nhược điểm |
|------|--------|-----------|
| **Cách 1** | Đơn giản, tự động merge | Khó kiểm soát nếu có conflict |
| **Cách 2** | Rõ ràng, dễ debug | Tốn thời gian hơn |
| **Cách 3** | Nhanh nhất | Không thích hợp nếu có nhiều conflict |

## 🎯 Khuyến nghị: Dùng Cách 2 (An toàn nhất)

```bash
git checkout main
git merge person1 -m "Merge person1: Auth + Home"
git merge person3 -m "Merge person3: Movies/Theater List"
git merge person4 -m "Merge person4: Seat Booking"
git merge person5 -m "Merge person5: UI/Themes"
git push origin main
```

---

## 🐛 Lỗi thường gặp

### Lỗi: "merge: origin - not something we can merge"

```bash
# ❌ SAI - Thiếu dấu / giữa origin và main
git merge origin main

# ✅ ĐÚNG - Thêm dấu /
git merge origin/main

# ✅ HOẶC dùng pull
git pull origin main
```

### Lỗi: "fatal: pathspec 'main' did not match any files"

```bash
# ❌ SAI - Branch chưa tồn tại hoặc tên sai
git checkout main

# ✅ Kiểm tra tên branch đúng
git branch -a

# ✅ Sau đó checkout branch đúng
git checkout <tên_branch_đúng>
```

### Lỗi: "Your branch and 'origin/main' have diverged"

```bash
# Nếu muốn giữ code của bạn
git merge origin/main

# Nếu muốn lấy code từ remote
git reset --hard origin/main
```

### Lỗi: "Updates were rejected because the remote contains work"

```bash
# Cần pull trước khi push
git pull origin <branch>

# Xử lý conflict nếu có
git add .
git commit -m "Resolve conflict"
git push origin <branch>
```

---

## 🔗 Merge Người 2 + Người 1 (Một số người cụ thể)

### Cách 1: Merge từ Person 1 vào Person 2

```bash
# 1. Chuyển sang branch person2
git checkout person2

# 2. Merge code từ person1 vào person2
git merge person1 -m "Merge person1 into person2: Add Auth + Home to Database layer"

# 3. Giải quyết xung đột nếu có
git status

# 4. Đẩy lên server
git push origin person2
```

### Cách 2: Merge cả Person 1 và 2 vào Main

```bash
# 1. Chuyển sang branch main
git checkout main

# 2. Merge person1
git merge person1 -m "Merge person1: Auth + Home"

# 3. Merge person2
git merge person2 -m "Merge person2: Room Database + Models + DAOs"

# 4. Đẩy lên server
git push origin main
```

### Cách 3: Merge nhiều người (Person 1, 2, 3, 4) loại bỏ Person 5

```bash
# 1. Chuyển sang branch main
git checkout main

# 2. Merge từng cái
git merge person1 -m "Merge person1: Auth + Home"
git merge person2 -m "Merge person2: Room Database + Models + DAOs"
git merge person3 -m "Merge person3: Movies/Theater List"
git merge person4 -m "Merge person4: Seat Booking"

# 3. Bỏ qua person5 (UI/Themes)
# (Không merge person5)

# 4. Đẩy lên server
git push origin main
```

### Cách 4: Merge tất cả trong 1 dòng (Person 2 + Person 1)

```bash
git checkout main && git merge person1 -m "Merge person1" && git merge person2 -m "Merge person2" && git push origin main
```

## 📋 Kịch bản merge phổ biến

| Kịch bản | Lệnh |
|---------|------|
| **Person 2 + Person 1** | `git checkout main && git merge person1 && git merge person2 && git push origin main` |
| **Person 1 + 2 + 3** | `git checkout main && git merge person1 && git merge person2 && git merge person3 && git push origin main` |
| **Tất cả (1+2+3+4+5)** | `git checkout main && git merge person1 && git merge person2 && git merge person3 && git merge person4 && git merge person5 && git push origin main` |
| **Loại trừ Person 5** | `git checkout main && git merge person1 && git merge person2 && git merge person3 && git merge person4 && git push origin main` |

## ⚠️ Nếu merge Person 2 + Person 1 có xung đột

```bash
# 1. Kiểm tra xung đột
git status

# 2. Sửa các file bị xung đột
# - Tìm <<<<<<, ======, >>>>>>
# - Giữ lại code cần thiết

# 3. Đánh dấu đã resolve
git add <file_xung_đột>

# 4. Hoàn tất merge
git commit -m "Resolve merge conflict between person1 and person2"

# 5. Đẩy lên
git push origin main
```

---
