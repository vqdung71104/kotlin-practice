import kotlin.math.abs

class PhanSo(var tu: Int = 1, var mau: Int = 1) {

    // Nhập phân số từ bàn phím
    fun nhap() {
        while (true) {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số: ")
            mau = readln().toInt()
            if (mau == 0) {
                println("Mẫu số không được bằng 0, vui lòng nhập lại!")
            } else {
                break
            }
        }
    }

    // In phân số
    fun inPhanSo() {
        println("$tu/$mau")
    }

    // Tìm ước chung lớn nhất của mẫu và tử (dùng cho tối giản)
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) abs(a) else gcd(b, a % b)
    }

    // Tối giản phân số
    fun toiGian() {
        val ucln = gcd(tu, mau)
        tu /= ucln
        mau /= ucln
        if (mau < 0) { // chuẩn hóa mẫu số dương
            tu = -tu
            mau = -mau
        }
    }

    // So sánh với phân số khác
    fun soSanh(ps: PhanSo): Int {
        val left = this.tu * ps.mau
        val right = ps.tu * this.mau
        return when {
            left < right -> -1 //trả về -1 nếu nhỏ hơn
            left == right -> 0 //trả về 0 nêu bằng nhau
            else -> 1          //trả về 1 nếu lớn hơn
        }
    }

    // Tính tổng với phân số khác
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = this.tu * ps.mau + ps.tu * this.mau //cộng tổng 2 tử số sau khi quy đồng
        val mauMoi = this.mau * ps.mau //quy đồng 2 mẫu số
        val ketQua = PhanSo(tuMoi, mauMoi)
        ketQua.toiGian()
        return ketQua
    }

    // Giá trị thực của phân số (dùng cho tìm max, sắp xếp)
    fun giaTri(): Double {
        return tu.toDouble() / mau //đưa mẫu số về dạng số thập phân
    }
}

fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val mangPS = Array(n) { PhanSo() }

    // Nhập mảng phân số
    println("Nhập các phân số:")
    for (i in 0 until n) {
        println("Phân số thứ ${i + 1}:")
        mangPS[i].nhap()
    }

    // In ra mảng vừa nhập
    println("\nMảng phân số vừa nhập:")
    for (ps in mangPS) {
        ps.inPhanSo()
    }

    // Tối giản và in
    println("\nMảng phân số sau khi tối giản:")
    for (ps in mangPS) {
        ps.toiGian()
        ps.inPhanSo()
    }

    // Tính tổng các phân số
    var tong = PhanSo(0, 1)
    for (ps in mangPS) {
        tong = tong.cong(ps)
    }
    println("\nTổng các phân số = ")
    tong.inPhanSo()

    // Tìm phân số lớn nhất
    var maxPS = mangPS[0]
    for (ps in mangPS) {
        if (ps.soSanh(maxPS) > 0) {
            maxPS = ps
        }
    }
    print("\nPhân số lớn nhất là: ")
    maxPS.inPhanSo()

    // Sắp xếp giảm dần
    mangPS.sortWith { a, b -> b.soSanh(a) }
    println("\nMảng phân số sau khi sắp xếp giảm dần:")
    for (ps in mangPS) {
        ps.inPhanSo()
    }
}