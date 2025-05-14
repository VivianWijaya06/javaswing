package week;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageCreator {
    public static JPanel createMainPage() {
        JPanel mainPage = new JPanel(new BorderLayout());
        mainPage.setBackground(Colors.GRID_BACKGROUND);
        
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Colors.GRID_BACKGROUND);
        
        JLabel welcomeLabel = new JLabel("Welcome to the NCT Family!");
        welcomeLabel.setFont(Fonts.WELCOME_FONT);
        welcomeLabel.setForeground(Colors.TEXT_COLOR);
        
        welcomePanel.add(welcomeLabel);
        mainPage.add(welcomePanel, BorderLayout.CENTER);
        
        return mainPage;
    }

    public static JPanel createMemberPage() {
        JPanel mainPage = new JPanel(new BorderLayout());
        mainPage.setBackground(Colors.GRID_BACKGROUND);
        mainPage.add(createMemberGrid(), BorderLayout.CENTER);
        return mainPage;
    }

    public static JPanel createNCT127Page() {
        return createUnitPage("NCT 127 Members", Colors.NCT127_COLOR, 
            member -> member.getUnitColor().equals(Colors.NCT127_COLOR));
    }

    public static JPanel createNCTDreamPage() {
        return createUnitPage("NCT Dream Members", Colors.NCTDREAM_COLOR, 
            member -> member.getUnitColor().equals(Colors.NCTDREAM_COLOR));
    }

    public static JPanel createWayVPage() {
        return createUnitPage("WayV Members", Colors.WAYV_COLOR, 
            member -> member.getUnitColor().equals(Colors.WAYV_COLOR));
    }

    public static JPanel createNCTWishPage() {
        return createUnitPage("NCT Wish Members", Colors.NCTWISH_COLOR, 
            member -> member.getUnitColor().equals(Colors.NCTWISH_COLOR));
    }

    public static JPanel createExMemberPage() {
        return createUnitPage("Ex Members", Colors.EXMEMBER_COLOR, 
            member -> member.getUnitColor().equals(Colors.EXMEMBER_COLOR));
    }

    public static JPanel createNCTUPage() {
        return createUnitPage("NCT U Members", Colors.NCTU_COLOR, 
            member -> member.getUnitColor().equals(Colors.NCTU_COLOR));
    }

    private static JPanel createUnitPage(String title, Color color, java.util.function.Predicate<MemberCard> filter) {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(Colors.DARK_BACKGROUND);
        
        JPanel content = new JPanel();
        content.setBackground(Colors.GRID_BACKGROUND);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(Fonts.TITLE_FONT);
        titleLabel.setForeground(Colors.TEXT_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        content.add(titleLabel);
        
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        gridPanel.setBackground(Colors.DARK_BACKGROUND);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        createMemberList().stream()
            .filter(filter)
            .forEach(member -> gridPanel.add(member));
        
        content.add(new JScrollPane(gridPanel));
        
        page.add(content, BorderLayout.CENTER);
        return page;
    }

    private static JScrollPane createMemberGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        gridPanel.setBackground(Colors.DARK_BACKGROUND);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        createMemberList().forEach(member -> 
            gridPanel.add(member)
        );

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private static List<MemberCard> createMemberList() {
        List<MemberCard> members = new ArrayList<>();
        // NCT 127 Members
        members.add(new MemberCard("Taeyong", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140353/taeyong-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Johnny", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140341/johnny-2-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Yuta", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140241/yuta-2-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Doyoung", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140754/doyoung-2-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Jaehyun", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140414/jaehyun-1-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Jungwoo", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140326/jungwoo-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Mark", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140420/mark-lee-364x563.jpg", Colors.NCT127_COLOR));
        members.add(new MemberCard("Haechan", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140426/haechan-364x563.jpg", Colors.NCT127_COLOR));
        
        // NCT Dream Members
        members.add(new MemberCard("Renjun", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140253/renjun-364x563.jpg", Colors.NCTDREAM_COLOR));
        members.add(new MemberCard("Jeno", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140347/jeno-364x564.jpg", Colors.NCTDREAM_COLOR));
        members.add(new MemberCard("Jaemin", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140224/jaemin-2-364x563.jpg", Colors.NCTDREAM_COLOR));
        members.add(new MemberCard("Chenle", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140409/chenle-2-364x563.jpg", Colors.NCTDREAM_COLOR));
        members.add(new MemberCard("Jisung", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140404/jisung-2-364x564.jpg", Colors.NCTDREAM_COLOR));
        
        // WayV Members
        members.add(new MemberCard("Kun", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140236/kun-364x563.jpg", Colors.WAYV_COLOR));
        members.add(new MemberCard("Ten", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140305/ten-364x563.jpg", Colors.WAYV_COLOR));
        members.add(new MemberCard("WinWin", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140310/winwin-364x563.jpg", Colors.WAYV_COLOR));
        members.add(new MemberCard("Xiaojun", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26141337/xiaojun-364x563.jpg", Colors.WAYV_COLOR));
        members.add(new MemberCard("Hendery", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140316/hendery-2-364x563.jpg", Colors.WAYV_COLOR));
        members.add(new MemberCard("YangYang", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140321/yangyang-2-364x563.jpg", Colors.WAYV_COLOR));
        
        // NCT Wish Members
        members.add(new MemberCard("Sion", "https://kprofiles.com/wp-content/uploads/2023/09/SION-4-900x600.jpg", Colors.NCTWISH_COLOR));
        members.add(new MemberCard("Riku", "https://kprofiles.com/wp-content/uploads/2023/09/RIKU-4-900x600.jpg", Colors.NCTWISH_COLOR));
        members.add(new MemberCard("Yushi", "https://kprofiles.com/wp-content/uploads/2023/09/YUSHI-4-533x800.jpg", Colors.NCTWISH_COLOR));
        members.add(new MemberCard("Sakuya", "https://kprofiles.com/wp-content/uploads/2023/09/SAKUYA-4-536x800.jpg", Colors.NCTWISH_COLOR));
        members.add(new MemberCard("Jaehee", "https://kprofiles.com/wp-content/uploads/2023/09/JAEHEE-3-900x600.jpg", Colors.NCTWISH_COLOR));
        members.add(new MemberCard("Ryo", "https://kprofiles.com/wp-content/uploads/2023/09/RYO-4-533x800.jpg", Colors.NCTWISH_COLOR));
        
        // Ex Members
        members.add(new MemberCard("Lucas", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140358/lucas-2-364x563.jpg", Colors.EXMEMBER_COLOR));
        members.add(new MemberCard("Shotaro", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140332/shotaro-364x563.jpg", Colors.EXMEMBER_COLOR));
        members.add(new MemberCard("Sungchan", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140337/sungchan-2-364x564.jpg", Colors.EXMEMBER_COLOR));
        members.add(new MemberCard("Taeil", "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/04/26140247/taeil-2-364x563.jpg", Colors.EXMEMBER_COLOR));
        members.add(new MemberCard("Wong Lucas", "https://up.yimg.com/ib/th?id=OIP.l8_QR6OdgBf4sa3VXRSIHQHaEK&pid=Api&rs=1&c=1&qlt=95&w=200&h=112", Colors.EXMEMBER_COLOR));

        // NCT U Members (who are also in other units)
        members.add(new MemberCard("Mark", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110911-534x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Ten", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174611-534x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Doyoung", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110602-533x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Jaehyun", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174538-533x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Haechan", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110439-533x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Jeno", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174719-533x800.jpg", Colors.NCTU_COLOR));
        members.add(new MemberCard("Jaemin", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110341-533x800.jpg", Colors.NCTU_COLOR));

        return members;
    }
}