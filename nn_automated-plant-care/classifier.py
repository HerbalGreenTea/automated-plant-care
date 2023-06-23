import onnxruntime as onnxrt
import numpy as np
from PIL import Image
from torchvision import transforms


class PlantCareClassifier:
    def __init__(self):
        self.violets = onnxrt.InferenceSession("Violets.onnx")
        self.monstera = onnxrt.InferenceSession("Monstera.onnx")
        self.pelargonium = onnxrt.InferenceSession("Pelargonium.onnx")

    def to_numpy(self, tensor):
        return tensor.detach().cpu().numpy() if tensor.requires_grad else tensor.cpu().numpy()

    def get_output(self, path, type_plant):
        onnx_output = None
        img = Image.open(path)
        resize = transforms.Resize([224, 224])
        img = resize(img)
        to_tensor = transforms.ToTensor()
        img_y = to_tensor(img)
        img_y.unsqueeze_(0)
        if type_plant == 'pelargonium':
            ort_inputs = {self.pelargonium.get_inputs()[0].name: self.to_numpy(img_y)}
            onnx_output = self.pelargonium.run(None, ort_inputs)
        elif type_plant == 'violets':
            ort_inputs = {self.violets.get_inputs()[0].name: self.to_numpy(img_y)}
            onnx_output = self.violets.run(None, ort_inputs)
        elif type_plant == 'monstera':
            ort_inputs = {self.monstera.get_inputs()[0].name: self.to_numpy(img_y)}
            onnx_output = self.monstera.run(None, ort_inputs)
        return onnx_output

    def get_bin_prediction(self, pre_res):
        probabilities = np.array(pre_res[0])
        predictions = np.argmax(probabilities, axis=1)
        return predictions

    def get_prediction(self, path, type_plant):
        pre_res = self.get_output(path, type_plant)
        bin_result = self.get_bin_prediction(pre_res)
        if bin_result[0] == 0:
            return "bad"
        else:
            return "good"

