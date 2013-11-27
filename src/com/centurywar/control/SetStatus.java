package com.centurywar.control;

//设置延迟时间
import java.io.IOException;

import net.sf.json.JSONObject;

import com.centurywar.Behave;
import com.centurywar.UsersModel;

public class SetStatus extends BaseControl {

	public SetStatus() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void betch(JSONObject jsonObj) {

		int type = jsonObj.getInt("type");
		int pik = jsonObj.getInt("pik");
		int value = jsonObj.getInt("value");
		int data = jsonObj.getInt("data");
		gameuid = jsonObj.getInt("gameuid");
		String sec = jsonObj.getString("sec");
		String username = jsonObj.getString("username");
		UsersModel am = new UsersModel(username, sec);
		// 有延时的开关
		if (type == 10 && data > 0 && false) {
			Behave be = new Behave(0);
			be.newInfo(am.client, am.gameuid, -data,
					getBehaver(type, pik, value, data));
		} else {
			JSONObject Jso = new JSONObject();
			Jso.put("gameuid", am.client);
			Jso.put("fromgameuid", gameuid);
			Jso.put("sendToArduino", getBehaver(type, pik, value, data));
			System.out.println(String.format("send to arduino %d:%s",
					am.client, getBehaver(type, pik, value, data)));
			sendToSocket(Jso, ConstantControl.SET_STATUS);
		}

	}

}
