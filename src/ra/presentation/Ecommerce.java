package ra.presentation;

import ra.business.CategoriesBussiness;
import ra.entity.Categories;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*************Ecommerce***********");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Ecommerce.displayCatalogMenu(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayCatalogMenu(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("**********CATEGORIES MANAGEMENT**********");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Hiển thị danh mục theo mã danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Ecommerce.displayListCategories();
                    break;
                case 2:
                    Ecommerce.createCatalog(scanner);
                    break;
                case 3:
                    Ecommerce.updateCatalog(scanner);
                    break;
                case 4:
                    Ecommerce.deleteCatalog(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExist);
    }

    public static void displayListCategories() {
        List<Categories> listCategories = CategoriesBussiness.findAll();
        listCategories.stream().forEach(System.out::println);
    }

    public static void createCatalog(Scanner scanner) {
        Categories catalogNew = new Categories();
        catalogNew.inputData(scanner);
        boolean result = CategoriesBussiness.create(catalogNew);
        if (result) {
            System.out.println("Thêm mới danh mục thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới danh mục");
        }
    }

    public static void updateCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        if (CategoriesBussiness.isExistCatalog(catalogId)) {
            //Đã tồn tại danh mục --> cập nhật danh mục
            //- Lấy thông tin danh mục cần cập nhật
            Categories catalogUpdate = CategoriesBussiness.getCatalogById(catalogId);
            boolean isExist = true;
            do {
                System.out.println("Chọn thông tin cần cập nhật:");
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả danh mục");
                System.out.println("3. Cập nhật trạng thái danh mục");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên danh mục cần cập nhật:");
                        catalogUpdate.setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("nhập vào mô tả danh mục cập nhật:");
                        catalogUpdate.setDescriptions(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập vào trạng thái cập nhật:");
                        catalogUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    default:
                        isExist = false;

                }
            } while (isExist);
            //Cập nhật danh mục
            boolean result = CategoriesBussiness.updateCatalog(catalogUpdate);
            if (result){
                System.out.println("Cập nhật thành công");
            }else{
                System.out.println("Có lỗi trong quá trình cập nhật");
            }
        } else {
            //Không tồn tại mã danh mục
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCatalog(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        //1. Kiểm tra danh mục có tồn tại không
        boolean isExist = CategoriesBussiness.isExistCatalog(catalogId);
        //2. Nếu tồn tại thực hiện xóa
        if (isExist){
            boolean result = CategoriesBussiness.delete(catalogId);
            if (result){
                System.out.println("Xóa danh mục thành công");
            }else{
                System.err.println("Có lỗi trong quá trình xóa danh mục");
            }
        }else{
            System.err.println("Mã danh mục không tồn tại");
        }
    }
}
