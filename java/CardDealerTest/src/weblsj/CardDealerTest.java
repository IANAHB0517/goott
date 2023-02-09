package weblsj;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CardDealerTest extends JFrame {
	String curCard = "AH";
	 
	public CardDealerTest(String title) {
		super(title);

		this.setVisible(true);
		this.setSize(new Dimension(480, 640));
	}

	@Override // anotation : 컴파일러에게 아래의 메서드가 오버라이딩 되었음을 알려주는 것.
			  // 메서드위에 골뱅이로 쓰여있으면 오버라이딩
			  // 오버라이딩 : 다른 클래스에서 같은 매개변수와 같은 이름으로 선언 (메서드가 가진 원래의 기능을 해쳐서는 안된다.)
	public void paint(Graphics g) { // 윈도우가 그려질 때 자동으로 호출되는 일종의 콜백 메서드
		// TODO Auto-generated method stub
		super.paint(g); // super 가 가진 메서드를 실행 
		
		String imagePath = "D:\\lecture\\java\\CardDealerTest\\cards\\" + curCard + ".png";
				// \(역슬러시) = 이스케이프 시퀀스 = 현재 시퀀스를 나간다?
		
//		g.drawOval(100, 100, 50, 50);
		URL imgUrl = null;
		try {
			imgUrl = new URL("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("잘못된 URL 주소입니다.");
			System.exit(0);
		}
		
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(imgUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("잘못된 URL 주소입니다.");
			System.exit(0);
		}
		g.drawImage(img, 0, 0, null); // 위에서 받은 url로 이미지를 출력
		
		File fPath = new File(imagePath);
		
		try {
			g.drawImage(ImageIO.read(fPath), 0, 0, null); // 읽어온 파일 경로로 이미지 출력 위의 출력 구문을 지우지 않으면 겹쳐져서 나온다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Dealer 김딜러 = new Dealer();

		System.out.println(김딜러.displayCard());

		김딜러.shuffleCard();
		System.out.println(김딜러.displayCard());

		System.out.println(김딜러.displayCard());
		for (int i = 0; i < Dealer.CARD_NUM; i++) {
			김딜러.pickCardAndRemoveArray();
		}

		CardDealerTest win = new CardDealerTest("포커");
	}

}
